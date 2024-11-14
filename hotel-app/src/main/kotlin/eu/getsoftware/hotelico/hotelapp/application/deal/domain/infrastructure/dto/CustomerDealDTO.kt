package eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties
import java.util.*

data class CustomerDealDTO(val initId: Long): BasicDTO<CustomerDomainEntityId>(/*initId*/)
{
    /**
     * owner activity
     */
    var activityId = 0L

    var consistencyId = 0L

    var timeValid = true

    var lastMinute = false

    var closed = false

    var hotelDomainId: HotelDomainEntityId? = null

    var senderDomainId: CustomerDomainEntityId? = null

    var dealDaysDuration = 0

    var totalMoney: Double? = null

    var validFrom: Date? = null

    var validTo: Date? = null

    var title = ""

    private var timeString = ""

    var activityArea = ""

    var dealCode = ""

    var tablePosition = ""

    var dealStatus = ""

    var shortDescription = ""

    var description = ""

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

    fun setPictureUrl(pictureUrl: String) {
        this.pictureUrl = pictureUrl
    }

    fun getPreviewPictureUrl(): String? {
        
        val picUrl = if (AppConfigProperties.isEmptyString(previewPictureUrl)) AppConfigProperties.PREVIEW_HOTEL_NOT_AVAILABLE_URL else previewPictureUrl

        return AppConfigProperties.addHostPrefixOnDemand(picUrl)
    }

    fun setPreviewPictureUrl(previewPictureUrl: String) {
        this.previewPictureUrl = previewPictureUrl
    }

    fun getTimeString(): String? {
        return if (consistencyId > 0) AppConfigProperties.getTimeFormatted(Date(consistencyId)) else null
    }
}
