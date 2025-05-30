package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.chat.messaging;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinDeletedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessagePayload;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.gateways.HotelGatewayService;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.HotelCommandProcessorEventService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
/*public*/ class HotelCommandProcessorEventServiceImpl extends HotelCommandProcessorEventService {
    
    public HotelCommandProcessorEventServiceImpl(HotelGatewayService hotelGatewayService) {
        super(hotelGatewayService);
    }
    
    private RedisTemplate redisTemplate; //central cache for messageIds

    private ConcurrentHashMap<String, DomainMessage<?>> localMessageIdCache = new ConcurrentHashMap<>();
    
    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "chat.message.processor.dev")
     void listener(ConsumerRecord<String, DomainMessage<? extends DomainMessagePayload>> record){

        String producerMsgKey = record.key();
        DomainMessage<? extends DomainMessagePayload> message = record.value();

        long offset = record.offset();
        int partition = record.partition();
        String uniqueMessageId = record.topic() + "-" + partition + "-" + offset;

        if(!existsInCache(uniqueMessageId, message))
        {
            DomainMessagePayload payload = message.getPayload();
            
            switch (message.getMessageType()){
                case "checkin.checkin.created.event" -> {
                    if (payload instanceof CheckinUpdatedEventPayload) 
                        onCheckinCreatedEvent((CheckinUpdatedEventPayload) payload);
                }
                case "checkin.checkin.deleted.event" -> {
                    if (payload instanceof CheckinDeletedEventPayload)
                        onCheckinDeletedEvent((CheckinDeletedEventPayload) payload);
                }
            }
        }

        System.out.println("Kafka listener payload: " + message.getPayload().toString() + ", uniqueMessageId: " + uniqueMessageId);
    }

    private Boolean existsInCache(String uniqueMessageId, DomainMessage<?> message) {
        
        boolean existsInLocalMap = localMessageIdCache.containsKey(uniqueMessageId);
        if(!existsInLocalMap)
        {
            localMessageIdCache.put(uniqueMessageId, message);
        }

        // Проверяем, существует ли уже этот уникальный ID в Redis
        Boolean existsInCache = redisTemplate.hasKey(uniqueMessageId);

        if (existsInCache == null || !existsInCache) {
            // Если такого ключа нет, сохраняем его в Redis
            redisTemplate.opsForValue().set(uniqueMessageId, "processed");
            System.out.println("Saved unique ID in Redis: " + uniqueMessageId);
        }
        
        return existsInCache;
    }
}
