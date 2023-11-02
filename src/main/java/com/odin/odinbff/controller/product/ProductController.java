package com.odin.odinbff.controller.product;

import java.util.List;
import java.util.stream.Collectors;

import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.PurveyorReposioty;
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
	
	private final PurveyorReposioty purveyorReposioty;
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;

	public ProductController(@Autowired final ProductRepository productRepository,
							 @Autowired final PurveyorReposioty purveyorReposioty,
							 @Autowired final BrandRepository brandRepository) {
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.purveyorReposioty = purveyorReposioty;
	}

	@GetMapping
	public List<ProductResponse> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream()
				.map(ProductResponse::new)
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<ProductResponse> save(@RequestBody  ProductFormRequest request) {
		Product product = request.toModel(brandRepository, purveyorReposioty);
		productRepository.save(product);
		return ResponseEntity.ok(new ProductResponse(product));
	}
}
