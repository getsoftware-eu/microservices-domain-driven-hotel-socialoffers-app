package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "languages", schema = "customer")
public class Language  implements Serializable
{

	private static final long serialVersionUID = 5215301020730273485L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Column(name = "descriptionShort", nullable = false, length = 10)
	private String descriptionShort;

	@Column(name = "descriptionLong")
	private String descriptionLong;

	//eugen: mappedBy entity.field on this Entity!
	@ManyToMany(mappedBy="languageSet")
	private Set<CustomerDBEntity> customerEntities = new HashSet<>();

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public long getId()
	{
		return id;
	}
}
