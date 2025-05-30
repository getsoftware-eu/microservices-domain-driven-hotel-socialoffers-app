package hotelico.service.booking.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.WallPostDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class WallPostDTO implements BasicDTO<WallPostDomainEntityId> {

    private final WallPostDomainEntityId domainEntityId;
    private final boolean active = true;
    
    private HotelDomainEntityId hotelId;
    private CustomerDomainEntityId senderId;

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
}
