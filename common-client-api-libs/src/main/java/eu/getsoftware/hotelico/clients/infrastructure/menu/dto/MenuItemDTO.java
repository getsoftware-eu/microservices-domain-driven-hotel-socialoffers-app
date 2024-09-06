package eu.getsoftware.hotelico.clients.infrastructure.menu.dto;

import eu.getsoftware.hotelico.common.dto.BasicDTO;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@With
@Getter
@SuperBuilder
public class MenuItemDTO extends BasicDTO{

     Long  orderId = 0L;

     int  orderIndex = 0;

     Long  hotelId  = 0L;

     long  cafeId = 0;

     long  senderId  = 0;

     boolean  delimiter = false;

     double  price = 0.0;

     int  amount = 0;

     String  title = "";

     String  shortDescription = "";

     String  description = "";

     String  pictureUrl = null;

     String  previewPictureUrl  = null;

     Timestamp  timestamp = null;
}
