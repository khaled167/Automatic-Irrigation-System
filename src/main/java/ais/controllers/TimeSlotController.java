package ais.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ais.helpers.TimeSlotHelper;
import ais.services.TimeSlotService;

@RestController
@RequestMapping("/time-slot")
public class TimeSlotController {

	@Autowired
	TimeSlotService timeSlotService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody TimeSlotHelper timeSlot) throws Exception {
		return timeSlotService.add(timeSlot);
	}
	@GetMapping("/sum")
	public Integer sum() {
		return timeSlotService.totalAmountOfWater(2l,(byte)0);
	}
}
