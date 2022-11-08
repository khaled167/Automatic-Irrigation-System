package ais.configurations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ais.exceptions.InvalidKeyException;
import ais.exceptions.TimeSlotIntersectionException;
import ais.models.TimeSlot;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidKeyException.class)
	public ResponseEntity<?> handleInvalidKeyException(InvalidKeyException ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid Primary Key \"ID\"");
	}

	@ExceptionHandler(TimeSlotIntersectionException.class)
	public ResponseEntity<?> handleTimeSlotIntersectionException(TimeSlotIntersectionException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body("There is an intersection with another time slot: " + TimeSlot.stringify(ex.getIntersectedTimeSlot()));

	}
}
