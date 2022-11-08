package eu.getsoftware.hotelico.customer.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import eu.getsoftware.hotelico.hotel.domain.CustomerHotelCheckin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Secondary data, not fetsch with every query
 */
@Entity
@Getter @Setter
@Table(name = "customer_details")
public class CustomerDetails
{
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private Date birthday;
	@Column
	private String company;
	@Column
	private String jobTitle;
	@Column
	private String city;
	@Column
	private String originalCity;
	@Column
	private String country;
	@Column
	private String jobDescriptor;
	@Column
	private String profileImageUrl;
	
	@OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pk.customer", cascade= CascadeType.ALL)
	private Set<CustomerHotelCheckin> customerHotelHistories = new HashSet<CustomerHotelCheckin>(0);
	
	public CustomerDetails()
	{
		super();
	}
}
