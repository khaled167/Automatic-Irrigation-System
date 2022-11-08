package ais.helpers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotHelper {
	@NotNull
	private Long landPlotId;

	@Range(min = 0, max = 6)
	private Byte day;

	@Range(min = 0, max = 23)
	private Integer initialHour;

	@Range(min = 0, max = 59)
	private Integer initialMinute;

	@Range(min = 0, max = 23)
	private Integer finalHour;

	@Range(min = 0, max = 59)
	private Integer finalMinute;

	@NotNull
	@Positive
	private Integer amountOfWater;

}
