package com.nexti.crud.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nexti.crud.entities.Cliente;
import com.nexti.crud.entities.Pedido;

public class PedidoDto {

	private Long id;
	private Long totalCompra;
	private Instant dataCompra;
	private Cliente cliente;
	
	private List<ProdutoDto> produtos = new ArrayList<>();
	
	public PedidoDto() {}
	
	public PedidoDto(Long id, Long totalCompra, Instant dataCompra, Cliente cliente,  List<ProdutoDto> produtos) {
		super();
		this.id = id;
		this.totalCompra = totalCompra;
		this.dataCompra = dataCompra;
		this.cliente = cliente;
		this.produtos = produtos;
	}
	
	public PedidoDto(Pedido pedido) {
		id = pedido.getId();
		totalCompra = pedido.getTotalCompra();
		dataCompra = pedido.getDataCompra();
		cliente = pedido.getCliente();
		produtos = pedido.getProdutos()
				.stream()
				.map(x -> new ProdutoDto(x))
				.collect(Collectors.toList());	// Preenchendo a lista de produtos. Copiar todos os produtos vindos da lsita de produtos da entidade Order e colocar dentro da lista de ProductsDTO
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Long totalCompra) {
		this.totalCompra = totalCompra;
	}

	public Instant getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Instant dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public  List<ProdutoDto> getProdutos() {
		return produtos;
	}

}
