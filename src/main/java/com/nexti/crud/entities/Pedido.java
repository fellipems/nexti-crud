package com.nexti.crud.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
	
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
    private Produto produto;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
