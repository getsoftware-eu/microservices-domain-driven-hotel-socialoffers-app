package eu.getsoftware.hotelico.service.menu.adapter.out.persistence.model;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "menu_user")
public class MenuUserMappedEntity implements Serializable
{
	private static final long serialVersionUID = -3552764230944489778L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String userId;
	
	@Column
	private String firstName;
	
	@Column
	private String email;
	
	public MenuUserMappedEntity()
	{
		
	}
	
	public MenuUserMappedEntity(CustomerDomainEntityId userId)
	{
		this();
		this.userId = userId.uuidValue();
	}
	
	public long getId()
	{
		return id;
	}
}
