package com.odin.odinbff.controller.brand;

import java.util.List;  
import java.util.stream.Collectors;

import com.odin.odinbff.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odin.odinbff.model.Brand;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin("*")

public class BrandController {
	@Autowired 
	private BrandRepository repository;
	
	@GetMapping
	public List<BrandFormRequest>getList(){
		return repository.findAll().stream()
				.map(BrandFormRequest::fromModel)
				.collect(Collectors.toList());
	}
	
	
	
	@PostMapping
	public ResponseEntity save(@RequestBody BrandFormRequest request) {
		Brand brand = request.toModel();
		repository.save(brand);
		return ResponseEntity.ok(BrandFormRequest.fromModel(brand));
		
	}

}
