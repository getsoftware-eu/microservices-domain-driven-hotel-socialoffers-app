package de.hotelico.model;

/**
 * Created by Eugen on 16.07.2015.
 */
import javax.persistence.Embeddable;
		import javax.persistence.ManyToOne;

@Embeddable
public class CustomerHotelCheckinId implements java.io.Serializable {

	private static final long serialVersionUID = 552154191271568694L;
	private Customer customer;
	private Hotel hotel;

	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer stock) {
		this.customer = stock;
	}

	@ManyToOne
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel category) {
		this.hotel = category;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerHotelCheckinId that = (CustomerHotelCheckinId) o;

		if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
		if (hotel != null ? !hotel.equals(that.hotel) : that.hotel != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (customer != null ? customer.hashCode() : 0);
		result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
		return result;
	}

}