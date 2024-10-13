package eu.getsoftware.hotelico.clients.common.error;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	
	private long id;
	
	public UserNotFoundException(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}