package ais.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ais.validations.annotations.ValidTimeSlot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "timeslots")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@ValidTimeSlot
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private LandPlot landPlot;

	@Range(min = 0, max = 6)
	private Byte day; // 0 is Sunday, 1 is Monday, 2 is Tuesday , ... 6 is Saturday

	@Range(min = 0, max = 23)
	private Integer initialHour;

	@Range(min = 0, max = 59)
	private Integer initialMinute;

	@Range(min = 0, max = 23)
	private Integer finalHour; // Must be greater than or equal to initial Hour

	@Range(min = 0, max = 59)
	private Integer finalMinute; // if finalHour equal initialHour , this field must be greater than
									// initialMinute
	@Transient
	@Setter(AccessLevel.NONE)
	private Integer duration; // Duration in minutes

	@Transient
	@Setter(AccessLevel.NONE)
	private String dayName;

	@NotNull
	@Positive
	private Integer amountOfWater;

	public Integer getDuration() {
		return (finalHour - initialHour - 1) * 60 + (60 - initialMinute + finalMinute);
	}

	public String getDayName() {
		return new String[] { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" }[this.getDay()];
	}

	// Can't use toString, because it will make infinity recursive calls, due to
	// the bidirectional relation with land plot.
	public static String stringify(TimeSlot timeSlot) {
		return "TimeSlot [id=" + timeSlot.getId() + ", land plot=" + timeSlot.getLandPlot().getId() + ", day="
				+ timeSlot.getDay() + ", initialHour=" + timeSlot.getInitialHour() + ", initialMinute="
				+ timeSlot.getInitialMinute() + ", finalHour=" + timeSlot.getFinalHour() + ", finalMinute="
				+ timeSlot.getFinalMinute() + ", duration=" + timeSlot.getDuration() + ", amountOfWater="
				+ timeSlot.getAmountOfWater() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amountOfWater, day, dayName, duration, finalHour, finalMinute, id, initialHour,
				initialMinute);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		return Objects.equals(amountOfWater, other.amountOfWater) && Objects.equals(day, other.day)
				&& Objects.equals(dayName, other.dayName) && Objects.equals(duration, other.duration)
				&& Objects.equals(finalHour, other.finalHour) && Objects.equals(finalMinute, other.finalMinute)
				&& Objects.equals(id, other.id) && Objects.equals(initialHour, other.initialHour)
				&& Objects.equals(initialMinute, other.initialMinute);
	}

}
