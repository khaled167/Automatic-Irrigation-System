package ais.helpers;

import javax.validation.constraints.NotBlank;
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
public class LandPlotHelper {

	@NotNull
	@Positive
	private Double cultivatedArea;

	@NotBlank
	private String agriculturalCrop;

	@NotNull
	private Integer locationX;
	@NotNull
	private Integer locationY;

}
