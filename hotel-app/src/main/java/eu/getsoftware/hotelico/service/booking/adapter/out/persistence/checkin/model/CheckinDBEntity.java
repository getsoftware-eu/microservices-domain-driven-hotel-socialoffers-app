package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableSet;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // Для Hibernate или других ORM
@Entity
@Table(name = "checkin", schema = "hotel")
//@AssociationOverrides({
//		@AssociationOverride(name = "pk.customer",
//				joinColumns = @JoinColumn(name = "CUSTOMER_ID")),
//		@AssociationOverride(name = "pk.hotel",
//				joinColumns = @JoinColumn(name = "HOTEL_ID")) })
public class CheckinDBEntity extends CheckinRootDomainEntity implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = -2949611288215768311L;

	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hotel_id_generator")
	@SequenceGenerator(name="hotel_id_generator", sequenceName = "hotel_id_seq")
	private long checkin_id;

//	@Embedded
//	@Convert(converter = CheckinDomainEntityIdConverter.class)
	@Embedded @Column(name = "domain_id", length = 50)
	public CheckinDomainEntityId getDomainEntityId() {return domainEntityId;};
	
//	@Embedded
//	@Convert(converter = CustomerDomainEntityIdConverter.class)
//	public CustomerDomainEntityId getCustomerDomainEntityId() {return customerDomainEntityId;};

//	@Embedded
//	@Convert(converter = HotelDomainEntityIdConverter.class)
	@Embedded @Column(name = "hotel_domain_id", length = 50)
	public HotelDomainEntityId getHotelDomainEntityId() {return hotelDomainEntityId;};

	@Version
	private Long version;
	
	//	@JsonView(ProductPartialUpdateView.class)
	private LocalDateTime createdAt;
	
//	@JsonView(CheckinPartialUpdateView.class)
	private Integer tenantId;

//	@JsonView(ProductPartialUpdateView.class)
	private boolean visible;


////	@JsonView(ProductPartialUpdateView.class)
//	private String name;
//
////	@JsonView(ProductPartialUpdateView.class)
//	private String description;



//	@Singular
//	@JsonView(ProductPartialUpdateView.class)
//	private Set<String> tags;

//	@Singular
//	@JsonView(AttributePartialUpdateView.class)
//	private ImmutableSet<Attribute> attributes;
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;

//	private CheckinRootDomainEntityId pk = new CheckinRootDomainEntityId();
	
	@Column(name = "fullCheckin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean fullCheckin = false;	
	
	@Column(name = "staffCheckin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean staffCheckin = false;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = false, length = 10)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = false, length = 10)
	private Date validTo;
	
//	public CheckinDbEntity() {
//		super();
//	}

//	@EmbeddedId
//	public CheckinRootDomainEntityId getPk() {
//		return pk;
//	}
//
//	public void setPk(CheckinRootDomainEntityId pk) {
//		this.pk = pk;
//	}
//	
//	public void setCustomer(CustomerDomainEntityId customerId) {
//		
//		getPk().setCustomerEntityId(customerId);
//	}

//	@Transient
//	public long getHotelId() {
//		
//		return getPk().getHotelEntityId();
//	}
	
//	@Transient
//	public long getCustomerId() {
//		return getPk().getCustomerEntityId();
//	}
//	
//	public void setHotelId(HotelDomainEntityId hotel) {
//		getPk().setHotelEntityId(hotel);
//	}
//
//	public int hashCode() {
//		return (getPk() != null ? getPk().hashCode() : 0);
//	}
// 

//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//		
//		CheckinDbEntity that = (CheckinDbEntity) o;
//		
//		if (getPk() != null ? !getPk().equals(that.getPk())
//				: that.getPk() != null)
//			return false;
//		
//		return true;
//	}

	/**
	 * eu: entity options!
	 * @param originalSet
	 * @param elementToRemove
	 * @return
	 * @param <T>
	 */

	private static <T> ImmutableSet.Builder<T> remove(ImmutableSet<T> originalSet, T elementToRemove) {
		ImmutableSet.Builder<T> builder = ImmutableSet.builder();
		originalSet.forEach(element -> {
			if (!element.equals(elementToRemove)) {
				builder.add(element);
			}
		});
		return builder;
	}

//	/**
//	 * @param tag tag to be added to this product
//	 * @return <tt>true</tt> if this product did not already contain the specified
//	 * tag
//	 */
//	public boolean addOrReplaceTag(@NotNull String tag) {
//		boolean contained = tags.contains(checkNotNull(tag));
//		tags = remove(tags, tag).add(tag).build();
//		return !contained;
//	}
//
//	/**
//	 * @param tag tag to be removed from this product, if present
//	 * @return <tt>true</tt> if this product contained the specified tag
//	 */
//	public boolean removeTag(@NotNull String tag) {
//		boolean contained = tags.contains(checkNotNull(tag));
//		tags = remove(tags, tag).build();
//		return contained;
//	}

//	/**
//	 * @param attribute attribute to be added to this product
//	 * @return <tt>true</tt> if this product did not already contain the specified
//	 * attribute
//	 */
//	public boolean addOrReplaceAttribute(@NotNull Attribute attribute) {
//		boolean contained = attributes.contains(checkNotNull(attribute));
//		attributes = remove(attributes, attribute).add(attribute).build();
//		return !contained;
//	}

//	/**
//	 * @param attribute attribute to be removed from this product, if present
//	 * @return <tt>true</tt> if this product contained the specified attribute
//	 */
//	public boolean removeAttribute(@NotNull Attribute attribute) {
//		boolean contained = attributes.contains(checkNotNull(attribute));
//		attributes = remove(attributes, attribute).build();
//		return contained;
//	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class ProductBuilder {
	}
}