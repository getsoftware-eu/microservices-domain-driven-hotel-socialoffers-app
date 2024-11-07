package eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto;

import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@With
@Getter
@SuperBuilder
@AllArgsConstructor
public class MenuOrderDTO extends BasicDTO{
    
    @With
    private final long hotelId = 0L;
    private final long senderId = 0L;
    private final int itemAmount = 0;
    private final String orderLocation = "";
    private final String firstName = "";
    private final String lastName = "";
    private final String customerComment = "";
    
    private final String orderStatus = null;
    private final double totalPrice = 0.0;
    private final double totalMoney = 0;
    private final String orderCode = "";
    private final String previewPictureUrl = null;
    private final Date validFrom = null;
    private final Date validTo = null;
    private final boolean orderInRoom = false;
    
    private final boolean closed = false;
    private final Timestamp  timestamp = null;
    private final List<MenuItemDTO> menuItems = new ArrayList<>();
    
    private final String timeString = null;

    private final String  getPreviewPictureUrl() {
        return previewPictureUrl!=null ? previewPictureUrl: AppConfigProperties.PREVIEW_MENU_NOT_AVAILABLE_URL;
    }

//    void setPreviewPictureUrl(String previewPictureUrl) {
//        this.previewPictureUrl = previewPictureUrl;
//    }
    
    private final String  getTimeString() {
        return (timestamp != null)? AppConfigProperties.getTimeFormatted(timestamp) : null;
    }

//    void setTimeString(String timeString) {
//        this.timeString = timeString;
//    }
}
