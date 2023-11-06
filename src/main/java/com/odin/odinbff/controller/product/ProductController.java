package com.odin.odinbff.controller.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.PurveyorReposioty;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.odin.odinbff.model.Product;
import com.odin.odinbff.model.repository.ProductRepository;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Api.Product.PRODUCT_RESOURCE)
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
	public List<ProductResponse> getAll(){
		List<Product> products = productRepository.findAll();
		return products.stream()
				.map(ProductResponse::new)
				.collect(Collectors.toList());
	}

	@GetMapping(Api.Product.PRODUCT_READ_BY_ID)
	public Optional<ProductResponse> get(@PathVariable final Long id) {
		return Optional.of(productRepository.getReferenceById(id)).map(ProductResponse::new);
	}

	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody  ProductFormRequest request,
												@Autowired UriComponentsBuilder uriBuild) {
		Product product = request.toModel(brandRepository, purveyorReposioty);
		productRepository.save(product);

		return ResponseEntity.created(
				uriBuild.path(Api.Product.PRODUCT_READ_BY_ID)
						.buildAndExpand(product.getId())
						.toUri()
		).build();
	}
}
