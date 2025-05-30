// path: hotelico/service/booking/application/deal/domain/infrastructure/dto/CustomerDealDTO.java

package hotelico.service.booking.application.deal.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerDealDTO implements BasicDTO<CustomerDomainEntityId> {

    private final CustomerDomainEntityId domainEntityId;
    private final boolean active;

    private long activityId = 0L;
    private long consistencyId = 0L;

    private boolean timeValid = true;
    private boolean lastMinute = false;
    private boolean closed = false;

    private HotelDomainEntityId hotelDomainId;
    private CustomerDomainEntityId senderDomainId;

    private int dealDaysDuration = 0;
    private Double totalMoney;

    private Date validFrom;
    private Date validTo;

    private String title = "";
    private String timeString = "";

    private String activityArea = "";
    private String dealCode = "";
    private String tablePosition = "";
    private String dealStatus = "";
    private String shortDescription = "";
    private String description = "";

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
        String picUrl = AppConfigProperties.isEmptyString(previewPictureUrl)
                ? AppConfigProperties.PREVIEW_HOTEL_NOT_AVAILABLE_URL
                : previewPictureUrl;

        return AppConfigProperties.addHostPrefixOnDemand(picUrl);
    }

    public String getTimeString() {
        return consistencyId > 0 ? AppConfigProperties.getTimeFormatted(new Date(consistencyId)) : null;
    }
}
