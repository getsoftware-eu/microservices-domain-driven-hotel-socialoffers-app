package de.hotelico.dto;

import de.hotelico.utils.ControllerUtils;

import java.util.Date;

class HotelActivityDto: BasicDTO
{

    var dealDaysDuration = 0

    var likeCounter = 0

    var subscribeCounter = 0

    var hotelCustomerNumber = 0

    var otherActivityNumber = 0

    /**
     * o: no limits
     * -1: no deals allowed
     */
    var limitDealNumber = 0

    var consistencyId = 0L

    var validDealId = 0L

    var orderIndex = 0
 
    var publishInWall = false

    var likedByMe = false

    var hidden = false

    var subscribeByMe = false

    var timeValid = true

    var dealAllowed = true

    var lastMinute = false

    var hotelId: Long? = 0L

    var senderId: Long? = 0L

    var validFrom: Date? = null

    var validTo: Date? = null

    var title = ""

    var activityArea = ""

    var hotelCity = ""

    var hotelName = ""

    var shortDescription = ""

    var description = ""

    var thirdPartyActivity = false

    private var pictureUrl: String? = null
    private var previewPictureUrl: String? = null

   constructor(): super()

    fun isActiveBoolean(): Boolean {
        return active
    }

    fun getValidToString(): String {
        return ControllerUtils.dateFormat.format(validTo)
    }

    fun getValidFromString(): String {
        return ControllerUtils.dateFormat.format(validFrom)
    }

    fun getPictureUrl(): String? {
        return ControllerUtils.addHostPrefixOnDemand(pictureUrl)
    }
    
    fun setPictureUrl(pictureUrl: String){
        this.pictureUrl = pictureUrl
    }
    
    fun getPreviewPictureUrl(): String {
        return previewPictureUrl ?: ControllerUtils.PREVIEW_ACTIVITY_NOT_AVAILABLE_URL
    }

    fun setPreviewPictureUrl(previewPictureUrl: String) {
        this.previewPictureUrl = previewPictureUrl
    }
}
