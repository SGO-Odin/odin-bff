package com.odin.odinbff.controller.produtos;

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
import com.odin.odinbff.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public List<ProdutoFormRequest>getLista(){
		return repository.findAll().stream()
				.map(ProdutoFormRequest::fromModel)
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody  ProdutoFormRequest request) {
		Product product = request.toModel();
		repository.save(product);
		return ResponseEntity.ok(ProdutoFormRequest.fromModel(product));
		
	}

}

