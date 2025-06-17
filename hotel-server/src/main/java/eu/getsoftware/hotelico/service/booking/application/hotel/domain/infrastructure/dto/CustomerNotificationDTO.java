// path: hotelico/service/booking/application/hotel/domain/infrastructure/dto/CustomerNotificationDTO.java

package eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CustomerNotificationDTO {

    private Long id = 0L;

    private String receiverId;

    private Long creationTime = 0L;

    /**
     * number of unread message from every sender
     */
    private Map<CustomerDomainEntityId, Integer> unreadChatProSenderCount;

    private Map<CustomerDomainEntityId, String> lastSeenOnlineHotelGuests;

    private String unreadChatsCounter;

    /**
     * current number of hotel activities
     */
    private Integer hotelUnreadActivitiesNumber;

    private Integer hotelGuestsNumber;

    private Integer myDealsNumber;

    private Integer myMenuOrdersNumber;

    private Integer[] hotelOnlineGuestIds;

    private Map<String, String> customerEvent = new HashMap<>();

    /**
     * last message from every sender
     */
    private Map<CustomerDomainEntityId, String> lastMessagesToMe;

    /**
     * last message time from every sender to customer
     */
    private Map<CustomerDomainEntityId, String> lastMessageTimesToMe;

    private Map<CustomerDomainEntityId, Boolean> lastMessageSeenByCustomer;

    private Map<CustomerDomainEntityId, Boolean> lastMessageDeliveredToCustomer;

    /**
     * current number of wall posts
     */
    private Integer wallMessageCounter;

    public void setCustomerEvent(Long customerId, Long hotelId, InnerHotelEvent event, String message, Long entityId) {
        customerEvent.put("senderDomainId", String.valueOf(customerId));
        customerEvent.put("hotelId", String.valueOf(hotelId));
        // customerEvent.put("event", event.getValue()); // if available
        customerEvent.put("message", message);
        // customerEvent.put("entity", event.getEntityString()); // if available
        customerEvent.put("entityId", String.valueOf(entityId));
    }

    public void setSocketPushCustomerEvent(String pushTitle, String pushMessage, String pushUrl, String pushIcon, String chatPartnerId) {
        customerEvent.put("pushTitle", pushTitle);
        customerEvent.put("pushIcon", pushIcon);
        customerEvent.put("pushUrl", pushUrl);
        customerEvent.put("pushMessage", pushMessage);
        customerEvent.put("chatPartnerId", chatPartnerId);
        customerEvent.put("pushTagId", "message" + chatPartnerId);
    }

    public void setMenuOrderInRoomNumber(int menuOrderInRoomNumber) {
        customerEvent.put("menuOrderInRoomNumber", String.valueOf(menuOrderInRoomNumber));
    }

    public void setLastMessageDelieveredToCustomer(Map<CustomerDomainEntityId, Boolean> lastMessageDelieveredToCustomer) {
        
    }
}
