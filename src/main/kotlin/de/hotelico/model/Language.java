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
@Table(name = "language")
public class Language  implements Serializable
{

	private static final long serialVersionUID = 5215301020730273485L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Column(name = "descriptionShort", nullable = false, length = 10)
	private String descriptionShort;

	@Column(name = "descriptionLong", nullable = true)
	private String descriptionLong;

	//eugen: mappedBy entity.field on this Entity!
	@ManyToMany(mappedBy="languageSet")
	private Set<Customer> customers = new HashSet<>();

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public long getId()
	{
		return id;
	}

	public String getDescriptionShort()
	{
		return descriptionShort;
	}

	public String getDescriptionLong()
	{
		return descriptionLong;
	}

	public Set<Customer> getCustomers()
	{
		return customers;
	}

	public void setDescriptionShort(String descriptionShort)
	{
		this.descriptionShort = descriptionShort;
	}

	public void setDescriptionLong(String descriptionLong)
	{
		this.descriptionLong = descriptionLong;
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
