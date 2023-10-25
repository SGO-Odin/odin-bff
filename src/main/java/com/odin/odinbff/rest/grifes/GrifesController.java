package com.odin.odinbff.rest.grifes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odin.odinbff.model.Grifes;
import com.odin.odinbff.model.repository.GrifesRepository;
import com.odin.odinbff.rest.produtos.ProdutoFormRequest;

@RestController
@RequestMapping("/api/grifes")
@CrossOrigin("*")

public class GrifesController {
	@Autowired 
	private GrifesRepository repository;
	
	@GetMapping
	public List<GrifesFormRequest>getLista(){
		return repository.findAll().stream()
				.map(GrifesFormRequest::fromModel)
				.collect(Collectors.toList());
	}
	
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody GrifesFormRequest request) {
		Grifes grifes = request.toModel();
		repository.save(grifes);
		return ResponseEntity.ok(GrifesFormRequest.fromModel(grifes));
		
	}

}
