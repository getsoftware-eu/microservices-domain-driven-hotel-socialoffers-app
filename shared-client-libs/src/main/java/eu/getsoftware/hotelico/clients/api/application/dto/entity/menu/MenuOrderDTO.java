package eu.getsoftware.hotelico.clients.api.application.dto.entity.menu;

import eu.getsoftware.hotelico.clients.common.domain.ids.MenuOrderDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MenuOrderDTO implements BasicDTO<MenuOrderDomainEntityId> {

    private final MenuOrderDomainEntityId domainEntityId;
    private final boolean active = true;

    public MenuOrderDTO(MenuOrderDomainEntityId domainEntityId) {
        this.domainEntityId = domainEntityId;
    }
    
    private  long hotelId = 0L;
    private  long senderId = 0L;
    private  int itemAmount = 0;
    private  String orderLocation = "";
    private  String firstName = "";
    private  String lastName = "";
    private  String customerComment = "";

    private String orderStatus = null;
    private double totalPrice = 0.0;
    private double totalMoney = 0;
    private String orderCode = "";
    private String previewPictureUrl = null;
    private Date validFrom = null;
    private Date validTo = null;
    private boolean orderInRoom = false;

    private boolean closed = false;
    private Timestamp  timestamp = null;
    private List<MenuItemDTO> menuItems = new ArrayList<>();

    private String timeString = null;

    private String  getPreviewPictureUrl() {
        return previewPictureUrl!=null ? previewPictureUrl: AppConfigProperties.PREVIEW_MENU_NOT_AVAILABLE_URL;
    }

//    void setPreviewPictureUrl(String previewPictureUrl) {
//        this.previewPictureUrl = previewPictureUrl;
//    }

    private String  getTimeString() {
        return (timestamp != null)? AppConfigProperties.getTimeFormatted(timestamp) : null;
    }

//    void setTimeString(String timeString) {
//        this.timeString = timeString;
//    }
}
