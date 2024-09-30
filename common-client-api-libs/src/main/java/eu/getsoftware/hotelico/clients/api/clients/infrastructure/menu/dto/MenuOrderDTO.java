package eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto;

import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.ControllerUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@With
@Getter
@SuperBuilder
public class MenuOrderDTO extends BasicDTO{
    long hotelId = 0L;
    long senderId = 0L;
    int itemAmount = 0;
    String orderLocation = "";
    String firstName = "";
    String lastName = "";
    String customerComment = "";
    
    @Setter
    String orderStatus = null;
    double totalPrice = 0.0;
    double totalMoney = 0;
    String orderCode = "";
    private String previewPictureUrl = null;
    Date validFrom = null;
    Date validTo = null;
    boolean orderInRoom = false;
    
    @Setter
    boolean closed = false;
    Timestamp timestamp = null;
    List<MenuItemDTO> menuItems;
    
    private String timeString = null;

    String getPreviewPictureUrl() {
        return previewPictureUrl!=null ? previewPictureUrl: ControllerUtils.PREVIEW_MENU_NOT_AVAILABLE_URL;
    }

    void setPreviewPictureUrl(String previewPictureUrl) {
        this.previewPictureUrl = previewPictureUrl;
    }
    
    String getTimeString() {
        return (timestamp != null)? ControllerUtils.getTimeFormatted(timestamp) : null;
    }

    void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
