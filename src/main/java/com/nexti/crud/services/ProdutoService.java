package com.nexti.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexti.crud.entities.Produto;
import com.nexti.crud.exceptions.ProdutoNaoEncontradoException;
import com.nexti.crud.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto cadastraProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public List<Produto> todosProdutos(){
		return produtoRepository.findAll();
	}
	
	public Produto atualizaProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto buscaProduto(String sku) {
		
		Optional<Produto> produto = produtoRepository.findProdutoByNome(sku);

		if(produto.isPresent()) {
			return produto.get();
		} else {
			throw new ProdutoNaoEncontradoException("Nenhum produto encontrado com SKU " + sku);
		}
	}
	
	public void deletaProduto(Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isPresent()) {
			produtoRepository.deleteProdutoById(id);
		} else {
			throw new ProdutoNaoEncontradoException("Nenhum produto com id " + id + "encontrado");
		}
	}
	
}
