package eu.getsoftware.hotelico.hotelapp.adapter.out.chat.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatKafkaListeners {

    private RedisTemplate redisTemplate; //central cache for messageIds

    private ConcurrentHashMap<String, DomainMessage<?>> localMessageIdCache = new ConcurrentHashMap<>();

    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "chat.message.processor.dev")
     void listener(ConsumerRecord<String, DomainMessage<?>> record){
        String producerMsgKey = record.key();
        DomainMessage<?> message = record.value();
        long offset = record.offset();
        int partition = record.partition();

        String uniqueMessageId = record.topic() + "-" + partition + "-" + offset;

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

        System.out.println("Kafka listener payload: " + message.getPayload().toString() + ", uniqueMessageId: " + uniqueMessageId);
    }
}
