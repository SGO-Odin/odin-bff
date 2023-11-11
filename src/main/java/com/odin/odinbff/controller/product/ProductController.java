package com.odin.odinbff.controller.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.odin.odinbff.controller.Api;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.PurveyorReposioty;
import com.odin.odinbff.service.ActivableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.odin.odinbff.model.product.Product;
import com.odin.odinbff.repository.ProductRepository;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Api.Product.PRODUCT_RESOURCE)
public class ProductController {
	
	private final PurveyorReposioty purveyorReposioty;
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;

	private final ActivableService activableService;


	public ProductController(@Autowired final ProductRepository productRepository,
							 @Autowired final PurveyorReposioty purveyorReposioty,
							 @Autowired final BrandRepository brandRepository,
							 @Autowired final ActivableService activableService) {
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.purveyorReposioty = purveyorReposioty;
		this.activableService = activableService;
	}

	@GetMapping
	public Set<ProductResponse> getAllActives(){
		Set<Product> products = productRepository.findAllByIsActive(true);
		return products.stream()
				.map(ProductResponse::new)
				.collect(Collectors.toUnmodifiableSet());
	}

	@GetMapping(Api.PATH_PARAM_ID)
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

	@PatchMapping(Api.PATH_PARAM_ID_INACTIVATE)
	public ResponseEntity<?> inactivate(@PathVariable final Long id) {
		return productRepository.findById(id).map(o -> {
			try {
				activableService.inactivate(o);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PatchMapping(Api.PATH_PARAM_ID_ACTIVATE)
	public ResponseEntity<?> activate(@PathVariable final Long id) {
		return productRepository.findById(id).map(o -> {
			try {
				activableService.activate(o);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
