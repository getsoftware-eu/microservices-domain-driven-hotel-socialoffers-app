package de.hotelico.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hotelico.utils.HotelEvent;

/**
 * Created by Eugen on 30.07.2015.
 */
class CustomerNotificationDto
{
    var id: Long = 0

    var receiverId: Long? = null

    var creationTime: Long = 0

    /**
     * number of unread message from every sender
     */
    var unreadChatProSenderCount: Map<Long, Integer>? = null

    var lastSeenOnlineHotelGuests: Map<Long, String>? = null

    var unreadChatsCounter: String? = null

    /**
     * current number of hotel activities
     */
    //	private Integer hotelActivitiesNumber;

    var hotelUnreadActivitiesNumber: Int? = null

    var hotelGuestsNumber: Int? = null

    var myDealsNumber: Int? = null

    var myMenuOrdersNumber: Int? = null

    var hotelOnlineGuestIds: Array<Int>? = null

    var customerEvent: MutableMap<String, String>  = mutableMapOf()

//	private Integer hotelOfflineGuestsNumber;

    /**
     * new guests for today
     */
//	private String[] newHotelCustomers;

    /**
     * last message from every sender
     */
    var lastMessagesToMe: Map<Long, String>? = null

    /**
     * last message time from every sender to customer
     */
    var lastMessageTimesToMe: Map<Long, String>? = null

    var lastMessageSeenByCustomer: Map<Long, Boolean>? = null

    var lastMessageDelieveredToCustomer: Map<Long, Boolean>? = null

    /**
     * currentNumber of wall posts
     */
    var wallMessageCounter: Int? = null

    fun setCustomerEvent(customerId: Long, hotelId: Long, event: HotelEvent, message: String, entityId: Long) {

        this.customerEvent["senderId"] = customerId.toString() 
        this.customerEvent["hotelId"] = hotelId.toString() 
        this.customerEvent["event"] = event.value
        this.customerEvent["message"] = message
        this.customerEvent["entity"] = event.entityString
        this.customerEvent["entityId"] = entityId.toString() 
    }

    fun setPushCustomerEvent(pushTitle: String, pushMessage: String, pushUrl: String, pushIcon: String, chatPartnerId: String) {

        this.customerEvent["pushTitle"] = pushTitle
        this.customerEvent["pushIcon"] = pushIcon
        this.customerEvent["pushUrl"] = pushUrl
        this.customerEvent["pushMessage"] = pushMessage
        this.customerEvent["chatPartnerId"] = chatPartnerId
        this.customerEvent["pushTagId"] = "message$chatPartnerId"
    }

    fun setMenuOrderInRoomNumber(menuOrderInRoomNumber: Int) {
        this.customerEvent["menuOrderInRoomNumber"] = menuOrderInRoomNumber.toString()
    }
}
