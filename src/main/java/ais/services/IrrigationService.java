package ais.services;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
	
import ais.models.Irrigation;
import ais.repositories.IrrigationRepository;
	
@Service
public class IrrigationService {
	
	@Autowired
	IrrigationRepository irrigationRepository;
	
	public Irrigation addIrrigation(Irrigation irrigation) {
		return irrigationRepository.save(irrigation);
	}
	public ResponseEntity<?> getAllIrrigations(){
		return ResponseEntity.ok(irrigationRepository.findAll());
	}
}	