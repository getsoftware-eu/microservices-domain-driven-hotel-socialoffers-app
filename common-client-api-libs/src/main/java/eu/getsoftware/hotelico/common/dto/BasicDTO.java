package eu.getsoftware.hotelico.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Eugen on 08.02.2016.
 */
@Getter
@NoArgsConstructor
public abstract class BasicDTO implements Serializable
{
	Long id = 0L;
	
	Long initId = 0L;
	
	Boolean active = true;
	
	String dtoType = this.getClass().getSimpleName().toLowerCase();
	
	protected BasicDTO(Long initId) {
        this.initId = initId;
    }
	
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
