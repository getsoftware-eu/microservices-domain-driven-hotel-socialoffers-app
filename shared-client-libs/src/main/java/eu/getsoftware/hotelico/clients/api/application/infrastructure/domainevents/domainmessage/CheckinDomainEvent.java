//package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage;
//
//import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Builder
//@Getter
//public class CheckinDomainEvent implements DomainMessage<CheckinUpdatedEventPayload> {
//
//    final DomainMessagePayload payload;
//
//    long tenantId = 0;
//    String messageType = "checkin.event";
//    LocalDateTime timestamp;
//    
////    public CheckinDomainEvent(CheckinUpdatedEventPayload payload) {
////        this.payload = payload;
////    }
//}
