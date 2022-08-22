package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	@java.io.Serial
	private static final long serialVersionUID = 8264441465654323976L;

	public ParkingNotFoundException(String id) {
		super(String.format("Parking not found with Id: %s", id));
	}

}
