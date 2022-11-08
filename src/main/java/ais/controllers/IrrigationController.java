package ais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ais.services.IrrigationService;

@RestController
@RequestMapping("/irrigation")
public class IrrigationController {
	@Autowired IrrigationService irrigationService;
	public ResponseEntity<?> getAllIrrigations(){
		return irrigationService.getAllIrrigations();
	}
	
}
