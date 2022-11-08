package ais.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ais.exceptions.InvalidKeyException;
import ais.helpers.LandPlotHelper;
import ais.models.LandPlot;
import ais.models.PolynomialRegression;
import ais.models.TimeSlot;
import ais.repositories.LandPlotRepository;

@Service
@Transactional
public class LandPlotService {

	@Autowired
	LandPlotRepository landPlotRepository;
	@Autowired
	TimeSlotService timeSlotService;

	public ResponseEntity<?> add(LandPlotHelper landPlot) throws Exception {
		return ResponseEntity.ok(landPlotRepository.save(this.build(landPlot)));
	}

	public ResponseEntity<?> edit(LandPlot landPlot) throws Exception {
		if (landPlot.getId() == (null) || !landPlotRepository.findById(landPlot.getId()).isPresent())
			throw new InvalidKeyException("Invalid ID number");
		return ResponseEntity.ok(landPlotRepository.save(landPlot));
	}

	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(landPlotRepository.findAll());
	}

	public LandPlot getLandPlot(Long id) {
		Optional<LandPlot> optional = landPlotRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	public Double predictAmountOfWaterByDay(Long id, Byte day) {
		LandPlot landPlot = getLandPlot(id);
		List<LandPlot> plots = landPlotRepository.findByAgriculturalCrop(landPlot.getAgriculturalCrop());
		double areas[] = new double[plots.size()];
		double results[] = new double[areas.length];
		int pointer = 0;
		for (LandPlot plot : plots) {
			areas[pointer] = plot.getCultivatedArea();
			results[pointer] = timeSlotService.totalAmountOfWater(id, day);
			pointer++;
		}
		return PolynomialRegression.predict(areas, results, landPlot.getCultivatedArea());
	}

	public Double predictTimeSlotByDay(Long id, Byte day) {
		LandPlot landPlot = getLandPlot(id);
		List<LandPlot> plots = landPlotRepository.findByAgriculturalCrop(landPlot.getAgriculturalCrop());
		double areas[] = new double[plots.size()];
		double results[] = new double[areas.length];
		int pointer = 0;
		for (LandPlot plot : plots) {
			areas[pointer] = plot.getCultivatedArea();
			List<TimeSlot> times = landPlot.getTimeSlots();
			Integer totalMinutes = 0;
			for (TimeSlot time : times)
				if (time.getDay() == day)
					totalMinutes += time.getDuration();
			results[pointer] = totalMinutes;
			pointer++;
		}
		return PolynomialRegression.predict(areas, results, landPlot.getCultivatedArea());
	}

	public LandPlot build(LandPlotHelper landPlotHelper) {
		LandPlot landPlot = new LandPlot();
		landPlot.setAgriculturalCrop(landPlotHelper.getAgriculturalCrop());
		landPlot.setCultivatedArea(landPlotHelper.getCultivatedArea());
		landPlot.setLocationX(landPlotHelper.getLocationX());
		landPlot.setLocationY(landPlotHelper.getLocationY());
		return landPlot;
	}
}
