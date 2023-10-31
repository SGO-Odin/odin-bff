package com.odin.odinbff.rest.brands;

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

import com.odin.odinbff.model.Brands;
import com.odin.odinbff.model.repository.BrandsRepository;
import com.odin.odinbff.rest.products.ProductFormRequest;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin("*")

public class BrandsController {
	@Autowired 
	private BrandsRepository repository;
	
	@GetMapping
	public List<BrandsFormRequest>getList(){
		return repository.findAll().stream()
				.map(BrandsFormRequest::fromModel)
				.collect(Collectors.toList());
	}
	
	
	
	@PostMapping
	public ResponseEntity save(@RequestBody BrandsFormRequest request) {
		Brands brands = request.toModel();
		repository.save(brands);
		return ResponseEntity.ok(BrandsFormRequest.fromModel(brands));
		
	}

}
