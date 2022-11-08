package ais.validations.implementations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ais.models.Irrigation;
import ais.validations.annotations.ValidIrrigation;

public class ValidIrrigationImpl implements ConstraintValidator<ValidIrrigation, Irrigation> {

	@Override
	public boolean isValid(Irrigation value, ConstraintValidatorContext context) {
		return value.getEndTime().isAfter(value.getStartTime());
	}

	@Override
	public void initialize(ValidIrrigation constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

}
