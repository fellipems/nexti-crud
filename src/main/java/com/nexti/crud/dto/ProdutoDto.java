package com.nexti.crud.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nexti.crud.entities.Produto;

public class ProdutoDto {

	private Long id;
	private String sku;
	private String nome;
	private String descricao;
	private double preco;
	private double quantidade;
	private List<PedidoDto> pedidos = new ArrayList<>();

	public ProdutoDto() { }

	public ProdutoDto(Long id, String sku, String nome, String descricao, double preco, double quantidade,
			List<PedidoDto> pedidos) {
		this.id = id;
		this.sku = sku;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.pedidos = pedidos;
	}

	public ProdutoDto(Produto produto) {
		id = produto.getId();
		sku = produto.getSku();
		nome = produto.getNome();
		descricao = produto.getDescricao();
		preco = produto.getPreco();
		quantidade = produto.getQuantidade();
		pedidos = produto.getPedidos()
				.stream()
				.map(x -> new PedidoDto(x))
				.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public List<PedidoDto> getPedidos() {
		return pedidos;
	}

}
