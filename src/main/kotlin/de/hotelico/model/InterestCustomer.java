package de.hotelico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Table(name = "interest_customer")
public class InterestCustomer implements Serializable
{

	private static final long serialVersionUID = -1994783091983352486L;
	@Id
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
	private Set<Customer> customers = new HashSet<>();

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public String getInterestArea()
	{
		return interestArea;
	}

	public Set<Customer> getCustomers()
	{
		return customers;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setInterestArea(String interestArea)
	{
		this.interestArea = interestArea;
	}

	public void setCustomers(Set<Customer> customers)
	{
		this.customers = customers;
	}

	public void setId(int id)
	{
//		this.id = id;
	}
}
