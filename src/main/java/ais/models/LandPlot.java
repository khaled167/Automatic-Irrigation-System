package ais.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "landplots")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LandPlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Positive
	private Double cultivatedArea;

	@NotBlank
	private String agriculturalCrop;

	/*
	 * Thought of the sensor device may need the coordinates, location, or just
	 * where specifically is this land located in order to direct letting the sensor
	 * irrigate it, but this would enforce us to validate and make sure there is not
	 * intersections between plots according to x,y and area
	 */

	@NotNull
	private Integer locationX;
	@NotNull
	private Integer locationY;

	@OneToMany(mappedBy = "landPlot")
	private List<TimeSlot> timeSlots = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(agriculturalCrop, cultivatedArea, id, locationX, locationY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LandPlot other = (LandPlot) obj;
		return Objects.equals(agriculturalCrop, other.agriculturalCrop)
				&& Objects.equals(cultivatedArea, other.cultivatedArea) 
				&& Objects.equals(locationX, other.locationX) && Objects.equals(locationY, other.locationY);
				
	}
}