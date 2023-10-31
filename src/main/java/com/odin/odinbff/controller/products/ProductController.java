package com.odin.odinbff.controller.products;

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

import com.odin.odinbff.model.Product;
import com.odin.odinbff.model.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public List<ProductFormRequest> getAllProducts(){
		List<Product> products = repository.findAll(); 
		return products.stream()
				.map(ProductFormRequest::fromModel)
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody  ProductFormRequest request) {
		Product product = request.toModel();
		repository.save(product);
		return ResponseEntity.ok(ProductFormRequest.fromModel(product));
	}
}
