package com.odin.odinbff.rest.produtos;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.coyote.Request; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odin.odinbff.model.Produto;
import com.odin.odinbff.model.repository.ProdutoRepository;
import com.odin.odinbff.rest.produtos.ProdutoFormRequest;
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
		Produto produto = request.toModel();
		repository.save(produto);
		return ResponseEntity.ok(ProdutoFormRequest.fromModel(produto));
		
	}

}

