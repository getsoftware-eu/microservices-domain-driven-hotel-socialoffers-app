package eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto;

import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@With
@Getter
@SuperBuilder
public class MenuItemDTO extends BasicDTO{

     private final long orderId = 0L;

     private final int  orderIndex = 0;

     private final long hotelId  = 0L;
     
     private final long cafeId = 0;
     
     private final long senderId  = 0;

     private final boolean delimiter = false;

     private final double price = 0.0;

     private final int amount = 0;

     private final String title = "";

     private final String shortDescription = "";

     private final String description = "";

     private final String pictureUrl = null;

     private final String previewPictureUrl  = null;

     private final Timestamp timestamp = null;
}
