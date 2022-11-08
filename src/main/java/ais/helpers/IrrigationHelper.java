package ais.helpers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IrrigationHelper {
	@NotNull
	@Positive
	private Long landPlot_id;
	
	@NotNull
	@Positive
	private Integer amountOfWater;
	
}