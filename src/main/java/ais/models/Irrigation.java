package ais.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ais.validations.annotations.ValidIrrigation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "irrigations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ValidIrrigation
public class Irrigation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime startTime;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime endTime;

	@Transient
	@Setter(AccessLevel.NONE)
	private Boolean finished;

	@Transient
	@Setter(AccessLevel.NONE)
	private Integer duration;

	@OneToOne
	private TimeSlot timeSlot;

	public Boolean hasFinished() {
		return endTime.compareTo(LocalDateTime.now()) <= 0;
	}

	public Integer getDuration() {
		return (int) ChronoUnit.MINUTES.between(startTime, endTime);
	}

	public static String stringify(Irrigation ir) {
		return "Irrigation [id=" + ir.getId() + ", startTime=" + ir.getStartTime() + ", endTime=" + ir.getEndTime()
				+ ", finished=" + ir.getFinished() + ", duration=" + ir.getDuration() + ", timeSlot=" + ir.getTimeSlot()
				+ "]";
	}

}
