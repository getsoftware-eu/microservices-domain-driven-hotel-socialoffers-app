package eu.getsoftware.hotelico.hotel.infrastructure.dto;

import eu.getsoftware.hotelico.clients.infrastructure.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.infrastructure.utils.ControllerUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;

@With
@Getter
@SuperBuilder()
public class HotelDTO extends BasicDTO
{
    private int rating = 0;
    
    private int activityNumber = 0;
    
    private int customerNumber = 0;
    
    private int anonymeGuestNumber = 0;
    
    private Long consistencyId = 0L;

    @Setter(AccessLevel.NONE)
    private long creationTime = 0L;

    private double kmFromMe = 0.0;
    
    private String name = null;
    
    private String wellcomeMessage = null;
    
    private String description = null;
    
    private String info = null;
    
    private String city = null;
    
    private String postalCode = null;
    
    private String street = null;
    
    private String house = null;
    
    private String phone = null;
    
    private String fax = null;
    
    private String email = null;
    
    private String currentHotelAccessCode= null;

//    private  String picture;

    private  String pictureUrl= null;

    private  String previewPictureUrl= null;
    
    private String website = null;
    
    private String mapLink = null;
    
    private Boolean virtualfalse;
    
    public HotelDTO(long initId){
        super(initId);
    }
    
    public long getCreationTime() {
      return (creationTime > 0)? creationTime : getId();
   }
    
    public String getPictureUrl() {
      return ControllerUtils.addHostPrefixOnDemand(pictureUrl);
   }
    
    public String getPreviewPictureUrl() {
        
        String picUrl = (ControllerUtils.isEmptyString(previewPictureUrl))? ControllerUtils.PREVIEW_HOTEL_NOT_AVAILABLE_URL : previewPictureUrl;

        return ControllerUtils.addHostPrefixOnDemand(picUrl);
   }
}
