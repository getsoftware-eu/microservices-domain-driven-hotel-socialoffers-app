//package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage;
//
//import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.HotelUpdateEventMessagePayload;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Builder
//@Getter
//public class HotelDomainEvent implements DomainMessage<HotelUpdateEventMessagePayload> {
//
//    private final DomainMessagePayload payload;
//
//    long tenantId = 0;
//    String messageType = "checkin.event";
//    LocalDateTime timestamp;
//    
////    public HotelDomainEvent(HotelUpdateEventMessagePayload payload) {
////        this.payload = payload;
////    }
//}
