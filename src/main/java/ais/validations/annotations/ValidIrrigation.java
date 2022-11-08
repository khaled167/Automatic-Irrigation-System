package ais.validations.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ais.validations.implementations.ValidIrrigationImpl;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ValidIrrigationImpl.class })

public @interface ValidIrrigation {
	String message() default "endTime must be strictly after startTime";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
