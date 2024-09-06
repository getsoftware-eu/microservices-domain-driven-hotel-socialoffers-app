package eu.getsoftware.hotelico.clients.infrastructure.menu;

import eu.getsoftware.hotelico.common.dto.BasicDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;

@With
@Getter
@SuperBuilder()
public class MenuDTO extends BasicDTO
{
	private Long consistencyId = 0L;
	
	@Setter(AccessLevel.NONE)
	private long creationTime = 0L;
	
	public MenuDTO(long initId){
		super(initId);
	}
	
	public long getCreationTime() {
		return (creationTime > 0)? creationTime : getId();
	}
	}
