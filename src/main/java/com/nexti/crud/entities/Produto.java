package com.nexti.crud.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100, unique = true)
	@NotNull(message = "SKU não pode ser vazio!")
	@NotEmpty(message = "SKU não pode ser vazio!")
	private String sku;

	@Column(nullable = false, length = 100)
	@NotNull(message = "Necessário informar um nome para o produto.")
	@NotEmpty(message = "Necessário informar um nome para o produto.")
	private String nome;

	@Column(nullable = false, length = 260)
	private String descricao;

	@Column(nullable = false)
	@NotNull(message = "Necessário informar um preço.")
	private double preco;

	@Column(nullable = false)
	@NotNull(message = "Necessário informar uma quantidade.")
	private double quantidade;
	
	@ManyToMany(mappedBy = "produtos", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonBackReference
	private List<Pedido> pedidos = new ArrayList<>();
	
	
//	@OneToMany
//	@JoinColumn(name = "pedido_id", referencedColumnName="id")
//	private List<Pedido> pedidos = new ArrayList<>();
	
	public Produto() {}
	
	public Produto(Long id, String sku, String nome, String descricao, double preco, double quantidade) {
		this.id = id;
		this.sku = sku;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
