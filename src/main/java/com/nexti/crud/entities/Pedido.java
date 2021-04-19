package com.nexti.crud.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable {	// Serializable pra transformar a classe em diferentes tipos de Streams. como por exemplo salva no BD, enviada por JSON

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long totalCompra;

	@Column(nullable = false)
	private Instant dataCompra;

	@ManyToOne // um cliente tem vários pedidos
	@JoinColumn(name = "cliente_id", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Cliente cliente;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonManagedReference
	@JoinTable(name = "tb_pedido_produto",
		joinColumns = { @JoinColumn(name = "fk_pedido",           referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "fk_produto",                   referencedColumnName = "id") })
	private Set<Produto> produtos = new HashSet<>();
	
//	@OneToOne
//	@JoinColumn(name = "id", referencedColumnName = "id")
//	private Cliente cliente;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidos")
//	private Set<Produto> produtos = new HashSet<>();  // conjunto. Pq set(não aceita repetição) e não list? não queremos admitir repetições do mesmo produto dentro do mesmo pedido
	
	public Pedido() {}
	
	public Pedido(Long id, Long totalCompra, Instant dataCompra, Cliente cliente) {
		this.id = id;
		this.totalCompra = totalCompra;
		this.dataCompra = dataCompra;
		this.cliente = cliente;  // Não bota collections no construtor pois adicionaremos em um for
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
