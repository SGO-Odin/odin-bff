package com.odin.odinbff.rest.grifes;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odin.odinbff.model.Grifes;
import com.odin.odinbff.model.repository.GrifesRepository;

@RestController
@RequestMapping("/api/grifes")
@CrossOrigin("*")

public class GrifesController {
	@Autowired 
	private GrifesRepository repository;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody GrifesFormRequest request) {
		Grifes grifes = request.toModel();
		repository.save(grifes);
		return ResponseEntity.ok(GrifesFormRequest.fromModel(grifes));
		
	}

}
