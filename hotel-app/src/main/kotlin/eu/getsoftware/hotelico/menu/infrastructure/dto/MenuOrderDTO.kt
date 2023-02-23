package eu.getsoftware.hotelico.menu.infrastructure.dto;

import eu.getsoftware.hotelico.clients.infrastructure.dto.BasicDTO
import eu.getsoftware.hotelico.clients.infrastructure.utils.ControllerUtils
import java.sql.Timestamp
import java.util.*

class MenuOrderDTO: BasicDTO
{
    var hotelId: Long? = 0L

    var senderId: Long? = 0L

    var itemAmount = 0

    var orderLocation = ""

    var firstName = ""

    var lastName = ""

    var customerComment = ""

    var orderStatus: String? = null

    var totalPrice = 0.0

    var totalMoney: Double? = null

    var orderCode = ""
    
    private var previewPictureUrl: String? = null

    var validFrom: Date? = null

    var validTo: Date? = null

    var orderInRoom = false

    var closed = false

    var timestamp: Timestamp? = null

    private var timeString: String? = null

    var menuItems = arrayOfNulls<MenuItemDTO>(0)

    constructor(): super()
    
    fun getPreviewPictureUrl(): String {
        return previewPictureUrl ?: ControllerUtils.PREVIEW_MENU_NOT_AVAILABLE_URL
    }

    fun setPreviewPictureUrl(previewPictureUrl: String) {
        this.previewPictureUrl = previewPictureUrl
    }
    
    fun getTimeString(): String? {
        return if (timestamp != null) ControllerUtils.getTimeFormatted(timestamp) else null
    }

    fun setTimeString(timeString: String) {
        this.timeString = timeString
    }
}
