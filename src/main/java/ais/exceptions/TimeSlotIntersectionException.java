package ais.exceptions;

import ais.models.TimeSlot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotIntersectionException extends RuntimeException {

	private static final long serialVersionUID = -2633010652144981031L;
	private TimeSlot intersectedTimeSlot;

	public TimeSlotIntersectionException() {
		super();
	}

	public TimeSlotIntersectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TimeSlotIntersectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeSlotIntersectionException(String message) {
		super(message);
	}

	public TimeSlotIntersectionException(Throwable cause) {
		super(cause);
	}

	public TimeSlotIntersectionException(TimeSlot intersectedTimeSlot) {
		super();
		this.intersectedTimeSlot = intersectedTimeSlot;
	}

	public TimeSlotIntersectionException(String message, TimeSlot intersectedTimeSlot) {
		super(message);
		this.intersectedTimeSlot = intersectedTimeSlot;
	}
}
