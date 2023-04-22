package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "menu_user")
public class MenuUserEntity implements Serializable
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
	
	public MenuUserEntity()
	{
		
	}
	
	public MenuUserEntity(long userId)
	{
		this();
		this.userId = userId;
	}
	
	public long getId()
	{
		return id;
	}
}
