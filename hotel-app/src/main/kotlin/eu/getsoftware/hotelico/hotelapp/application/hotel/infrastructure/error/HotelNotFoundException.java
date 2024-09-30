package eu.getsoftware.hotelico.hotelapp.application.hotel.infrastructure.error;

@SuppressWarnings("serial")
public class HotelNotFoundException extends RuntimeException {
	
	private long id;
	
	public HotelNotFoundException(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}