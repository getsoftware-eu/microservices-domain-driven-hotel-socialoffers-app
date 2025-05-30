package hotelico.service.booking.adapter.out.persistence.customer.model;

import hotelico.service.booking.application.customer.domain.model.ICustomerDetails;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Secondary data, not fetsch with every query
 */
@Entity
@Getter @Setter
@Table(name = "customer_details", schema = "customer")
public class CustomerDetails implements ICustomerDetails
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
	@Column
	private String profileImageLink; // S3 key
	
//	@OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pk.customer", cascade= CascadeType.ALL)
	@Column
	@ElementCollection
	private Set<String> customerHotelIdHistories = new HashSet<String>(0);
	
	public CustomerDetails()
	{
		super();
	}
	
	public Optional<String> getProfileImageLink()
	{
		return Optional.ofNullable(profileImageLink);
	}
	
	public void setProfileImageLink(String profileImageLink)
	{
		this.profileImageLink = profileImageLink;
	}
}
