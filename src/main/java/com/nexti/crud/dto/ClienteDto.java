package com.nexti.crud.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nexti.crud.entities.Cliente;

public class ClienteDto {
	
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	
	private List<PedidoDto> pedidos = new ArrayList<>();
	
	public ClienteDto() { }

	public ClienteDto(Long id, String nome, String cpf, LocalDate dataNascimento, List<PedidoDto> pedidos) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.pedidos = pedidos;
	}
	
	public ClienteDto(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		dataNascimento = cliente.getDataNascimento();
		pedidos = cliente.getPedidos()
				.stream()
				.map(x -> new PedidoDto(x))
				.collect(Collectors.toList());	// Preenchendo a lista de produtos. Copiar todos os produtos vindos da lsita de produtos da entidade Order e colocar dentro da lista de ProductsDTO
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<PedidoDto> getPedidos() {
		return pedidos;
	}	
	
}
