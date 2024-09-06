package eu.getsoftware.hotelico.hotel.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.infrastructure.exception.ApiErrorResponse;
import eu.getsoftware.hotelico.hotel.application.infrastructure.error.HotelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HotelApiExceptionHandler
{
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleApiException(HotelNotFoundException ex) {
		
		ApiErrorResponse response =
				new ApiErrorResponse("error-0001",
						"No user found with ID " + ex.getId());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}