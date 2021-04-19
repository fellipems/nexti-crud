package com.nexti.crud.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nexti.crud.dto.ProdutoDto;
import com.nexti.crud.exceptions.ProdutoNaoEncontradoException;
import com.nexti.crud.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService produtoService;
	
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> getListarProdutos() {
		List<ProdutoDto> produtos = produtoService.todosProdutos();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@GetMapping("/procurar/sku/{sku}")
	public ResponseEntity<ProdutoDto> getProdutoSku(@PathVariable("sku") String sku) { // retorna um produto com o respectivo SKU
		ProdutoDto produtoDto = produtoService.buscaProdutoSku(sku);
		return new ResponseEntity<>(produtoDto, HttpStatus.OK);
	}
	
	@GetMapping("/procurar/{nome}")	// traz produtos contendo o nome informado
	public ResponseEntity<List<ProdutoDto>> getProdutosPorNome(@PathVariable("nome") String nome) { // retorna um produto com o respectivo SKU
		List<ProdutoDto> produtoDto = produtoService.buscaProdutoNome(nome);
		if(!produtoDto.isEmpty()) {
    		return new ResponseEntity<>(produtoDto, HttpStatus.OK);
    	} else {
    		throw new ProdutoNaoEncontradoException("Nenhum produto encontrado com nome " + nome);
    	}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<ProdutoDto> cadastraProduto(@RequestBody @Valid ProdutoDto produtoDto) {
		produtoDto = produtoService.cadastraProduto(produtoDto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produtoDto.getId()).toUri();	// chamada que fazemos para criar uma Uri que corresponde ao recurso que criamos
		return ResponseEntity.created(uri).body(produtoDto);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletaProduto(@PathVariable("id") Long id) {
		produtoService.deletaProduto(id);
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
