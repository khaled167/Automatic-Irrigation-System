package ais.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ais.helpers.LandPlotHelper;
import ais.models.LandPlot;
import ais.services.LandPlotService;

@RestController
@RequestMapping("/landplot")
public class LandPlotController {

	@Autowired
	LandPlotService landPlotService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody LandPlotHelper landPlot) throws Exception {
		return landPlotService.add(landPlot);
	}

	@PutMapping("/edit")
	public ResponseEntity<?> edit(@Valid @RequestBody LandPlot landPlot) throws Exception {
		return landPlotService.edit(landPlot);
	}

	@GetMapping("/get-all")
	public ResponseEntity<?> getAll() {
		return landPlotService.getAll();
	}
	
	@GetMapping("/predict-water-amount")
	public Double predictWaterAmount() {
		return landPlotService.predictAmountOfWaterByDay(2l,(byte)1);
	}
	@GetMapping("/predict-time-slot")
	public Double predictTimeSlot() {
		return landPlotService.predictTimeSlotByDay(2l,(byte)1);
	}
}
