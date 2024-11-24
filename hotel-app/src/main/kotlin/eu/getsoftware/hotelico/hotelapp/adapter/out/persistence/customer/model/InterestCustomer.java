package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model;

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
@Table(name = "interest_customer", schema = "customer")
class InterestCustomer implements Serializable
{

	private static final long serialVersionUID = -1994783091983352486L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "interestArea", nullable = false)
	private String interestArea;

	//eugen: mappedBy entity.field on this Entity!
	@ManyToMany(mappedBy="interests")
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
