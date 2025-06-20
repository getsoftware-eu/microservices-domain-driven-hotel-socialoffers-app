package eu.getsoftware.hotelico.clients.api.application.dto.entity.menu;

import eu.getsoftware.hotelico.clients.common.domain.ids.MenuItemDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

import java.sql.Timestamp;

@With
@Getter
@RequiredArgsConstructor
public class MenuItemDTO implements BasicDTO<MenuItemDomainEntityId>{

     private final MenuItemDomainEntityId domainEntityId;
     private final boolean active = true;

     private final long orderId = 0L;

     private final int orderIndex = 0;

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
