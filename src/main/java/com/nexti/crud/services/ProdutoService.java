package com.nexti.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexti.crud.dto.PedidoDto;
import com.nexti.crud.dto.ProdutoDto;
import com.nexti.crud.entities.Pedido;
import com.nexti.crud.entities.Produto;
import com.nexti.crud.exceptions.ClienteNaoEncontradoException;
import com.nexti.crud.exceptions.ProdutoNaoEncontradoException;
import com.nexti.crud.repositories.PedidoRepository;
import com.nexti.crud.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	private final PedidoRepository pedidoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
		this.produtoRepository = produtoRepository;
		this.pedidoRepository = pedidoRepository;
	}
	
	@Transactional
	public ProdutoDto cadastraProduto(ProdutoDto produtoDto) {
		Produto produto = new Produto(null,
				produtoDto.getSku(),
				produtoDto.getNome(),
				produtoDto.getDescricao(),
				produtoDto.getPreco(),
				produtoDto.getQuantidade());
		for (PedidoDto p : produtoDto.getPedidos()) {
			Pedido pedido = pedidoRepository.getOne(p.getId());
			produto.getPedidos().add(pedido);
		}
		produto = produtoRepository.save(produto);
		return new ProdutoDto(produto);
	}
	
	public List<ProdutoDto> todosProdutos(){
		List<Produto> produtos = produtoRepository.findAllByOrderByNomeAsc();
		return produtos.stream()
				.map(x -> new ProdutoDto(x))   // .map aplica uma função para todos os elementos da lista, transformando-os em uma nova lista
				.collect(Collectors.toList());
	}
	
	public Produto atualizaProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto buscaProdutoSku(String sku) {
		
		Optional<Produto> produto = produtoRepository.findProdutoBySku(sku);
		
		if(produto.isPresent()) {
			return produto.get();
		} else {
			throw new ProdutoNaoEncontradoException("Nenhum produto encontrado com SKU " + sku);
		}
	}
	
	public List<Produto> buscaProdutoNome(String nome) {
		return produtoRepository.findProdutoByNomeContaining(nome)
				.orElseThrow(() -> 
				new ClienteNaoEncontradoException("Nenhum produto encontrado com nome " + nome));
	}
	
	@Transactional
	public void deletaProduto(Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);

		if(produto.isPresent()) {
			produtoRepository.deleteProdutoById(id);
		} else {
			throw new ProdutoNaoEncontradoException("Nenhum produto com id " + id + " encontrado");
		}
	}
	
}
