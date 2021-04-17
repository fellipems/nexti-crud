package com.nexti.crud.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexti.crud.dto.PedidoDto;
import com.nexti.crud.dto.ProdutoDto;
import com.nexti.crud.entities.Pedido;
import com.nexti.crud.entities.Produto;
import com.nexti.crud.repositories.PedidoRepository;
import com.nexti.crud.repositories.ProdutoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	
	private final ProdutoRepository produtoRepository;	
	
	@Autowired
	public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
	}
	
	@Transactional(readOnly = true)
	public List<PedidoDto> todosPedidos() {
		List<Pedido> list = pedidoRepository.findPedidosComProdutos();
		return list.stream()
				.map(x -> new PedidoDto(x))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public PedidoDto inserirPedido(PedidoDto dto) {
		Pedido pedido = new Pedido(null, 
				dto.getTotalCompra(), 
				Instant.now(), 
				dto.getCliente()); // Não bota collections no construtor pois adicionaremos em um for
		for (ProdutoDto p : dto.getProdutos()) {
			Produto produto = produtoRepository.getOne(p.getId());
			pedido.getProdutos().add(produto);
		}
		pedido = pedidoRepository.save(pedido);
		return new PedidoDto(pedido);
	}
}
