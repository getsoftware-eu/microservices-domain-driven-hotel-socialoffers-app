package eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.ActivityDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties
import java.time.LocalDate

data class HotelActivityDTO(var hotelId: HotelDomainEntityId): BasicDTO<ActivityDomainEntityId>()
{
    val initId: Long = 0
    
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
    
    var senderDomainId: CustomerDomainEntityId? = null

    var validFrom: LocalDate? = null

    var validTo: LocalDate? = null

    var title = ""

    var activityArea = ""

    var hotelCity = ""

    var hotelName = ""

    var shortDescription = ""

    var description = ""

    var thirdPartyActivity = false

    private var pictureUrl: String? = null
    private var previewPictureUrl: String? = null

    fun isActiveBoolean(): Boolean {
        return active
    }

    fun getValidToString(): String {
        return AppConfigProperties.dateFormat.format(validTo)
    }

    fun getValidFromString(): String {
        return AppConfigProperties.dateFormat.format(validFrom)
    }

    fun getPictureUrl(): String? {
        return AppConfigProperties.addHostPrefixOnDemand(pictureUrl)
    }
    
    fun setPictureUrl(pictureUrl: String){
        this.pictureUrl = pictureUrl
    }
    
    fun getPreviewPictureUrl(): String {
        return previewPictureUrl ?: AppConfigProperties.PREVIEW_ACTIVITY_NOT_AVAILABLE_URL
    }

    fun setPreviewPictureUrl(previewPictureUrl: String) {
        this.previewPictureUrl = previewPictureUrl
    }
    
    constructor(param: Long) : this(HotelDomainEntityId(param.toString()))
}
