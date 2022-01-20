package de.hotelico.dto;

import de.hotelico.utils.ControllerUtils;

public class HotelDTO: BasicDTO
{
    var rating: Int = 0

    var activityNumber = 0

    var customerNumber = 0

    var anonymeGuestNumber = 0

    var consistencyId: Long? = 0L

    private var creationTime: Long = 0L

    var kmFromMe = 0.0

    var name: String? = null

    var wellcomeMessage: String? = null

    var description: String? = null

    var info: String? = null

    var city: String? = null

    var postalCode: String? = null

    var street: String? = null

    var house: String? = null

    var phone: String? = null

    var fax: String? = null

    var email: String? = null

    var currentHotelAccessCode: String? = null

//    private String picture;

    private var pictureUrl: String? = null

    private var previewPictureUrl: String? = null

    var website: String? = null

    var mapLink: String? = null

    var virtual: Boolean = false

   constructor(): super()
   

   fun getCreationTime(): Long? {
      return if (creationTime > 0) creationTime else id.toLong()
   }

   fun setCreationTime(creationTime: Long) {
      this.creationTime = creationTime
   }
   
   fun getPictureUrl(): String? {
      return ControllerUtils.addHostPrefixOnDemand(pictureUrl)
   }

   fun setPictureUrl(pictureUrl: String) {
      this.pictureUrl = pictureUrl
   }

   fun getPreviewPictureUrl(): String? {
      val picUrl = if (ControllerUtils.isEmptyString(previewPictureUrl)) ControllerUtils.PREVIEW_HOTEL_NOT_AVAILABLE_URL else previewPictureUrl

      return ControllerUtils.addHostPrefixOnDemand(picUrl)
   }

   fun setPreviewPictureUrl(previewPictureUrl: String) {
      this.previewPictureUrl = previewPictureUrl
   }
}
