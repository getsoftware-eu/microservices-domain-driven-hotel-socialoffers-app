// path: hotelico/service/booking/application/hotel/domain/infrastructure/dto/ResponseDTO.java

package hotelico.service.booking.application.hotel.domain.infrastructure.dto;

/**
 * Created by Eugen on 09.08.2015.
 */
public class ResponseDTO {

	private int id = 0;
	private String message;
	private boolean error = false;

	public ResponseDTO() {
	}

	public ResponseDTO(String message, boolean error) {
		this.message = message;
		this.error = error;
	}

	// --- Getters and Setters ---

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
