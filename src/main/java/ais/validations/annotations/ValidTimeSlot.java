package ais.validations.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ais.validations.implementations.ValidTimeSlotImpl;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ValidTimeSlotImpl.class })

public @interface ValidTimeSlot {
	String message() default "initial time(initialHour, initialMinute) is before final time(finalHour,finalMinute)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
