package eu.getsoftware.hotelico.clients.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Created by Eugen on 08.02.2016.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BasicDTO<D extends EntityIdentifier> implements Serializable
{
	Long id = 0L;

	D domainId = null;
	
	@NonNull
	Long initId = null;
	
	Boolean active = true;
	
	String dtoType = this.getClass().getSimpleName().toLowerCase();
	
//	protected BasicDTO(Long initId) {
//        this.initId = initId;
//    }
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setInitId(Long initId)
	{
		this.initId = initId;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
}
