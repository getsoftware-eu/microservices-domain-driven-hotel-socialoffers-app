package eu.getsoftware.hotelico.hotel.dto;

import java.sql.Timestamp;

class MenuItemDto: BasicDTO {

     constructor() : super()

     var  orderId = 0L

     var  orderIndex = 0

     var  hotelId: Long? = 0L

     var  cafeId = 0

     var  senderId: Long? = 0

     var  delimiter = false

     var  price = 0.0

     var  amount = 0

     var  title = ""

     var  shortDescription = ""

     var  description = ""

     var  pictureUrl: String? = null

     var  previewPictureUrl: String? = null

     var  timestamp: Timestamp? = null
}
