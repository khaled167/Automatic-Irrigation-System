package ais.validations.implementations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ais.models.TimeSlot;
import ais.validations.annotations.ValidTimeSlot;

public class ValidTimeSlotImpl implements ConstraintValidator<ValidTimeSlot, TimeSlot> {

	@Override
	public boolean isValid(TimeSlot value, ConstraintValidatorContext context) {
//		return false;
		if (value.getFinalHour() < value.getInitialHour())
			return false;
		if (value.getFinalHour() > value.getInitialHour())
			return true;
		return value.getFinalMinute() > value.getInitialMinute();

	}

	@Override
	public void initialize(ValidTimeSlot constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

}
