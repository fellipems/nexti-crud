package com.nexti.crud.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;


@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@Column(nullable = false, length = 100)
	@NotNull(message = "Nome não pode ser vazio!")
	@NotEmpty(message = "Nome não pode ser vazio!")
	private String nome;

	@Column(nullable = false, length = 11, unique = true)
	@CPF
	@NotNull(message = "CPF não pode ser vazio!")
	@NotEmpty(message = "CPF não pode ser vazio!")
	private String cpf;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy") 
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) // um cliente tem um pedido mas um pedido tem vários cliente
	@JsonIgnore
	@JsonBackReference
	private List<Pedido> pedidos = new ArrayList<>();
	
//	@ManyToMany
//	@JoinTable(name = "tb_pedido_produto",
//			joinColumns = @JoinColumn(name = "cliente_id"), // Join columns é a chave estrangeira que referencía a classe onde estou
//			inverseJoinColumns = @JoinColumn(name = "pedido_id")) // a chave estrangeira que vai referenciar a outra tabela, correspondente ao tipo Produto
//	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente () {
		
	}

	public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
