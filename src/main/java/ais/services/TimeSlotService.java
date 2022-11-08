package ais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ais.exceptions.InvalidKeyException;
import ais.exceptions.TimeSlotIntersectionException;
import ais.helpers.TimeSlotHelper;
import ais.models.TimeSlot;
import ais.repositories.TimeSlotRepository;

@Service
@Transactional
public class TimeSlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;
	@Autowired
	LandPlotService landPlotService;

	public boolean isIntersected(TimeSlotHelper timeSlot) {
		return timeSlotRepository.numberOfIntersections(timeSlot.getDay(), timeSlot.getInitialHour(),
				timeSlot.getFinalHour(), timeSlot.getInitialMinute(), timeSlot.getFinalMinute()) != 0;
	}

	public ResponseEntity<?> add(TimeSlotHelper timeSlot) throws Exception {
		if (landPlotService.getLandPlot(timeSlot.getLandPlotId()) == null)
			throw new InvalidKeyException("Invalid ID Number");
		System.out.println(timeSlot);
		if (isIntersected(timeSlot))
			throw new TimeSlotIntersectionException("Intersection of TimeSlot Exception with another Time Slot: ",
					timeSlotRepository.intersectedTimeSlot(timeSlot.getDay(), timeSlot.getInitialHour(),
							timeSlot.getFinalHour(), timeSlot.getInitialMinute(), timeSlot.getFinalMinute()));
		return ResponseEntity.ok(timeSlotRepository.save(this.build(timeSlot)));
	}

	public TimeSlot build(TimeSlotHelper timeSlotHelper) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setLandPlot(landPlotService.getLandPlot(timeSlotHelper.getLandPlotId()));
		timeSlot.setAmountOfWater(timeSlotHelper.getAmountOfWater());
		timeSlot.setInitialHour(timeSlotHelper.getInitialHour());
		timeSlot.setInitialMinute(timeSlotHelper.getInitialMinute());
		timeSlot.setFinalHour(timeSlotHelper.getFinalHour());
		timeSlot.setFinalMinute(timeSlotHelper.getFinalMinute());
		timeSlot.setDay(timeSlotHelper.getDay());
		return timeSlot;
	}
	public Integer totalAmountOfWater(Long id,Byte day) {
		return timeSlotRepository.totalAmoutOfWater(id,day);
	}
}
