package eu.getsoftware.hotelico.clients.api.clients.dto.entity.menu;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.MenuDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

@With
@Getter
@SuperBuilder()
public class MenuDTO extends BasicDTO<MenuDomainEntityId>
{
	private final Long consistencyId = 0L;
	
	private final long creationTime = 0L;
	
//	public MenuDTO(long initId){
//		super(initId);
//	}
	
	public long getCreationTime() {
		return (creationTime > 0)? creationTime : id;
	}
	}
