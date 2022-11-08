package ais.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import ais.models.Irrigation;
import ais.models.TimeSlot;

@Service
/*
 * This is a Dummy Service, in the production phase it is replaced with the real
 * machine API
 */

public class SensorDeviceService {
	@Autowired
	ThreadPoolTaskScheduler taskScheduler;
	@Autowired
	IrrigationService irrigationService;

	public void addToSensor(TimeSlot timeSlot) {
		CronTrigger cron = new CronTrigger(timeSlot.getInitialMinute() + " " + timeSlot.getInitialHour() + " ? " + "* "
				+ timeSlot.getDayName());
		taskScheduler.schedule(() -> {
			Irrigation ir = new Irrigation();
			LocalDateTime now = LocalDateTime.now();
			ir.setStartTime(now);
			ir.setEndTime(now.plusMinutes(timeSlot.getDuration()));
			ir.setTimeSlot(timeSlot);
			irrigationService.addIrrigation(ir);
			System.out.println(cron.toString()+"\n"+now.toString()+"\n"+Irrigation.stringify(ir));
		}, cron);

	}
}