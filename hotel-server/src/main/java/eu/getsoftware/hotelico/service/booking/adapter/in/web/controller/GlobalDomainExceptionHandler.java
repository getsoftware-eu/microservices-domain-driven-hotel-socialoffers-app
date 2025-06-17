package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.exception.JsonError;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.exception.domain.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDomainExceptionHandler {

    /**
     * eu: auto reply HttpSTATUS on BusinessException
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<JsonError> handleBusinessException(BusinessException ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("error", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new JsonError("BUSINESS_ERROR", ex.getMessage()));
    }

    /**
     * eu: auto reply HttpSTATUS on EntityNotFoundException
     * @param ex
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<JsonError> handleValidationException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new JsonError("BUSINESS_ERROR", ex.getMessage()));
    }

    /**
     * eu: auto reply HttpSTATUS on MethodArgumentNotValidException
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorResponse.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * eu: auto reply HttpSTATUS on other Exception
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonError> handleGenericException(Exception ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("error", "Internal server error");
//        errorResponse.put("details", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new JsonError("SERVER_ERROR", "An unexpected error occurred"));

    }
}