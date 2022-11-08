package eu.getsoftware.hotelico.domain.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import eu.getsoftware.hotelico.domain.hotel.CustomerHotelCheckin;
import lombok.AccessLevel;
import lombok.Setter;

/**
 * Secondary data, not fetsch with every query
 */
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
