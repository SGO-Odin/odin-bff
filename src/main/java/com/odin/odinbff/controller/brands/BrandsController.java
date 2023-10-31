package com.odin.odinbff.controller.brands;

<<<<<<<< HEAD:src/main/java/com/odin/odinbff/rest/brands/BrandsController.java
package com.odin.odinbff.rest.brands;
========
package com.odin.odinbff.controller.grifes;
>>>>>>>> main:src/main/java/com/odin/odinbff/controller/grifes/GrifesController.java

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

<<<<<<<< HEAD:src/main/java/com/odin/odinbff/rest/brands/BrandsController.java
import com.odin.odinbff.model.Brands;
import com.odin.odinbff.model.repository.BrandsRepository;
========
import com.odin.odinbff.model.Grifes;
import com.odin.odinbff.repository.GrifesRepository;
>>>>>>>> main:src/main/java/com/odin/odinbff/controller/grifes/GrifesController.java

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
