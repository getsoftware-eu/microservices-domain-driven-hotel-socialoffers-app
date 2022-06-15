package eu.getsoftware.hotelico.hotel.dto;

import java.util.Date;

import eu.getsoftware.hotelico.hotel.utils.ControllerUtils;

class CustomerDealDto: BasicDTO
{
    /**
     * owner activity
     */
    var activityId = 0L

    var consistencyId = 0L

    var timeValid = true

    var lastMinute = false

    var closed = false

    var hotelId: Long? = 0L

    var senderId: Long? = 0L

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

    constructor(): super()

    fun isActiveBoolean(): Boolean {
        return active
    }

    fun getValidToString(): String {
        return eu.getsoftware.hotelico.hotel.utils.ControllerUtils.dateFormat.format(validTo)
    }

    fun getValidFromString(): String {
        return eu.getsoftware.hotelico.hotel.utils.ControllerUtils.dateFormat.format(validFrom)
    }

    fun getPictureUrl(): String? {
        return eu.getsoftware.hotelico.hotel.utils.ControllerUtils.addHostPrefixOnDemand(pictureUrl)
    }

    fun setPictureUrl(pictureUrl: String) {
        this.pictureUrl = pictureUrl
    }

    fun getPreviewPictureUrl(): String? {
        
        val picUrl = if (eu.getsoftware.hotelico.hotel.utils.ControllerUtils.isEmptyString(previewPictureUrl)) eu.getsoftware.hotelico.hotel.utils.ControllerUtils.PREVIEW_HOTEL_NOT_AVAILABLE_URL else previewPictureUrl

        return eu.getsoftware.hotelico.hotel.utils.ControllerUtils.addHostPrefixOnDemand(picUrl)
    }

    fun setPreviewPictureUrl(previewPictureUrl: String) {
        this.previewPictureUrl = previewPictureUrl
    }

    fun getTimeString(): String? {
        return if (consistencyId > 0) eu.getsoftware.hotelico.hotel.utils.ControllerUtils.getTimeFormatted(Date(consistencyId)) else null
    }
}
