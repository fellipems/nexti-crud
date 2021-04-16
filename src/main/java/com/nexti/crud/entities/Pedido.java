package com.nexti.crud.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pedido implements Serializable {	// Serializable pra transformar a classe em diferentes tipos de Streams. como por exemplo salva no BD, enviada por JSON

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long totalCompra;

	@Column(nullable = false)
	private Instant dataCompra;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente", referencedColumnName = "id")
	private Cliente cliente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "produto", referencedColumnName = "id")
	private Set<Produto> produtos = new HashSet<>();  // conjunto. Pq set(não aceita repetição) e não list? não queremos admitir repetições do mesmo produto dentro do mesmo pedido
	
	public Pedido() {}
	
	public Pedido(Long id, Long totalCompra, Instant dataCompra, Cliente cliente, Set<Produto> produtos) {
		this.id = id;
		this.totalCompra = totalCompra;
		this.dataCompra = dataCompra;
		this.cliente = cliente;
		this.produtos = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Produto> getProdutos() {
		return produtos;
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

}
