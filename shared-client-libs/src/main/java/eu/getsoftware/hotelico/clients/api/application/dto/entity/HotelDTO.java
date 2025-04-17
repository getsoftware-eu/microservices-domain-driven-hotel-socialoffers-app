package eu.getsoftware.hotelico.clients.api.application.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;

@With
@Getter
@Setter
@SuperBuilder()
public class HotelDTO extends BasicDTO<HotelDomainEntityId>
{
	private final int rating = 0;
	
	private final int activityNumber = 0;
	
	private final int customerNumber = 0;
	
	private final int anonymeGuestNumber = 0;
	
	private final long consistencyId = 0L;
	
//	@Setter(AccessLevel.NONE)
	private final long creationTime = 0L;
	
	@With
	private final double kmFromMe = 0.0;
	
	private final String name = null;
	
	private final String welcomeMessage = null;
	
	private final String description = null;
	
	private final String info = null;
	
	private final String city = null;
	
	private final String postalCode = null;
	
	private final String street = null;
	
	private final String house = null;
	
	private final String phone = null;
	
	private final String fax = null;
	
	private final String email = null;
	
	private final String currentHotelAccessCode= null;
	
	//    private  String picture;
	
	private final String pictureUrl= null;
	
	private final String previewPictureUrl= null;
	
	private final String website = null;
	
	private final String mapLink = null;
	
	private final boolean virtual = false;
	
//	public HotelDTO(long initId){
//		super(initId);
//	}
	
	public long getCreationTime() {
		return (creationTime > 0)? creationTime : id;
	}
	
	public String getPictureUrl() {
		return AppConfigProperties.addHostPrefixOnDemand(pictureUrl);
	}
	
	public String getPreviewPictureUrl() {
		
		String picUrl = (AppConfigProperties.isEmptyString(previewPictureUrl))? AppConfigProperties.PREVIEW_HOTEL_NOT_AVAILABLE_URL : previewPictureUrl;
		
		return AppConfigProperties.addHostPrefixOnDemand(picUrl);
	}

	public void setKmFromMe(double kms) {
		
	}

	public void withKmFromMe(double kms) {
		
	}
}
