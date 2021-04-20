package com.nexti.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexti.crud.dto.ClienteDto;
import com.nexti.crud.dto.ProdutoDto;
import com.nexti.crud.entities.Cliente;
import com.nexti.crud.entities.Produto;
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
		produto = produtoRepository.save(produto);
		return new ProdutoDto(produto);
	}
	
	public List<ProdutoDto> todosProdutos(){
		List<Produto> produtos = produtoRepository.findAllByOrderByNomeAsc();
		return produtos.stream()
				.map(x -> new ProdutoDto(x))   // .map aplica uma função para todos os elementos da lista, transformando-os em uma nova lista
				.collect(Collectors.toList());
	}
	
	public ProdutoDto buscaProdutoSku(String sku) {
		
		Optional<Produto> resultado = produtoRepository.findProdutoBySku(sku);
		
		if(resultado.isPresent()) {
			Produto produto = resultado.get();
			return new ProdutoDto(produto);
		} else {
			throw new ProdutoNaoEncontradoException("Nenhum produto com SKU " + sku + " encontrado");
		}		
	}
	
	public List<ProdutoDto> buscaProdutoNome(String nome) {
		
		List<Produto> produtos = produtoRepository.findProdutoByNomeContaining(nome);
		
		return produtos.stream()
				.map(x -> new ProdutoDto(x))
				.collect(Collectors.toList());
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

	public ProdutoDto atualizaProduto(Long id, ProdutoDto produtoDto) {
		
		Optional<Produto> retornoDb = produtoRepository.findById(id);
		
		if(retornoDb.isPresent()) {
			Produto produtoAtualizado = retornoDb.get();
			//produtoAtualizado.setId(produtoDto.getId());
			produtoAtualizado.setDescricao(produtoDto.getDescricao());
			produtoAtualizado.setNome(produtoDto.getNome());
			produtoAtualizado.setPreco(produtoDto.getPreco());
			produtoAtualizado.setQuantidade(produtoDto.getQuantidade());
			produtoAtualizado.setSku(produtoDto.getSku());
			produtoRepository.save(produtoAtualizado);
			return new ProdutoDto(produtoAtualizado);
		} else {
			throw new ProdutoNaoEncontradoException("Produto de id " + id + " não encontrado!");
		}
	}
	
}
