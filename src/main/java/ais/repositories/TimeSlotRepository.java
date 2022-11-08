package ais.repositories;

import org.springframework.stereotype.Repository;

import ais.models.TimeSlot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM timeslots WHERE day = ?1 and not "
			+ "((final_hour < ?2 or initial_hour > ?3) or " + "( initial_hour = ?3 and initial_minute >= ?5 ) or "
			+ "(final_hour = ?2 and final_minute <= ?4))")
	Long numberOfIntersections(Byte day, Integer initialHour, Integer finalHour, Integer initialMinute,
			Integer finalMinute);

	@Query(nativeQuery = true, value = "SELECT * FROM timeslots WHERE day = ?1 and not "
			+ "((final_hour < ?2 or initial_hour > ?3) or " + "( initial_hour = ?3 and initial_minute >= ?5 ) or "
			+ "(final_hour = ?2 and final_minute <= ?4))")
	TimeSlot intersectedTimeSlot(Byte day, Integer initialHour, Integer finalHour, Integer initialMinute,
			Integer finalMinute);
	
	@Query(nativeQuery = true, value = "SELECT SUM(amount_of_water) from timeslots WHERE land_plot_id = ?1 and day = ?2")
	Integer totalAmoutOfWater(Long landplot_id,Byte day);
}
