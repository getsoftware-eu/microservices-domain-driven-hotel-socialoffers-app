package eu.getsoftware.hotelico.domain.customer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "interest_customer")
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
	private Set<CustomerRootEntity> customerEntities = new HashSet<>();

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public long getId()
	{
		return id;
	}
}
