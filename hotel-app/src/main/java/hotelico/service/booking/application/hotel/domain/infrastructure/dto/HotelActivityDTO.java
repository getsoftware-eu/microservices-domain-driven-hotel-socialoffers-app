// path: hotelico/service/booking/application/hotel/domain/infrastructure/dto/HotelActivityDTO.java

package hotelico.service.booking.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class HotelActivityDTO implements BasicDTO<ActivityDomainEntityId> {

    private final ActivityDomainEntityId domainEntityId;
    private final boolean active = true;
    
    private HotelDomainEntityId hotelId;

    private final long initId = 0;

    private int dealDaysDuration = 0;
    private int likeCounter = 0;
    private int subscribeCounter = 0;
    private int hotelCustomerNumber = 0;
    private int otherActivityNumber = 0;

    /**
     * 0: no limits
     * -1: no deals allowed
     */
    private int limitDealNumber = 0;

    private long consistencyId = 0;
    private long validDealId = 0;
    private int orderIndex = 0;

    @Getter
    private boolean publishInWall = false;
    private boolean likedByMe = false;
    private boolean hidden = false;
    private boolean subscribeByMe = false;
    private boolean timeValid = true;
    private boolean dealAllowed = true;
    private boolean lastMinute = false;

    private CustomerDomainEntityId senderDomainId;
    private LocalDate validFrom;
    private LocalDate validTo;

    private String title = "";
    private String activityArea = "";
    private String hotelCity = "";
    private String hotelName = "";
    private String shortDescription = "";
    private String description = "";
    private boolean thirdPartyActivity = false;

    private String pictureUrl;
    private String previewPictureUrl;

    public String getValidToString() {
        return validTo != null ? AppConfigProperties.dateFormat.format(validTo) : "";
    }

    public String getValidFromString() {
        return validFrom != null ? AppConfigProperties.dateFormat.format(validFrom) : "";
    }

    public String getPictureUrl() {
        return AppConfigProperties.addHostPrefixOnDemand(pictureUrl);
    }

    public String getPreviewPictureUrl() {
        return previewPictureUrl != null ? previewPictureUrl : AppConfigProperties.PREVIEW_ACTIVITY_NOT_AVAILABLE_URL;
    }

    public void setSequenceId(long initId) {
    }
}
