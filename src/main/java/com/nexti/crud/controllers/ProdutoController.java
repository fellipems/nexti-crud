package com.nexti.crud.controllers;

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

import com.nexti.crud.entities.Produto;
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
	public ResponseEntity<List<Produto>> getListarProdutos() {
		List<Produto> produtos = produtoService.todosProdutos();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<Produto> getProduto(@PathVariable("sku") String sku) { // um produto
		Produto produto = produtoService.buscaProduto(sku);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> cadastraProduto(@RequestBody @Valid Produto produto) {
		produtoService.cadastraProduto(produto);
		Produto novoProduto = produtoService.cadastraProduto(produto);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletaProduto(@PathVariable("id") Long id) {
		produtoService.deletaProduto(id);
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
