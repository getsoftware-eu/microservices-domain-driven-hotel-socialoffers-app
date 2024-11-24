package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model;

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
	private long userId;
	
	@Column
	private String firstName;
	
	@Column
	private String email;
	
	public MenuUserMappedEntity()
	{
		
	}
	
	public MenuUserMappedEntity(long userId)
	{
		this();
		this.userId = userId;
	}
	
	public long getId()
	{
		return id;
	}
}
