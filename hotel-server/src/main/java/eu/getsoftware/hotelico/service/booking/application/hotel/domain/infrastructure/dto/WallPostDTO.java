package eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.WallPostDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class WallPostDTO implements BasicDTO<WallPostDomainEntityId> {

    private final WallPostDomainEntityId domainEntityId;
    private final boolean active = true;
    
    @Setter
    private HotelDomainEntityId hotelId;
    private final CustomerDomainEntityId senderId;

    @Setter
    private String message;
    private String senderName;
    private String sendTimeString;
    private boolean hotelStaff;
    private Map<String, String> specialContent;

    private Long sequenceId;
    private String dtoType;
    private long creationTime;

    // Getters and setters
   
    
    public Map<String, String> getSpecialContent() {
        return Collections.unmodifiableMap(specialContent);
    }

    public void setHotelId(HotelDomainEntityId domainEntityId) {
        this.hotelId= domainEntityId;
    }

    public void setSequenceId(long id) {
        
    }
}
