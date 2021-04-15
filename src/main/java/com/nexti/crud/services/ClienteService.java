package com.nexti.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexti.crud.entities.Cliente;
import com.nexti.crud.exceptions.ClienteNaoEncontradoException;
import com.nexti.crud.repositories.ClienteRepository;

@Service
public class ClienteService {
// lógica de negócio
	
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente adicionaCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> todosClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente atualizaCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente buscaCliente(String nome) {
		return clienteRepository.findClienteByNome(nome)
				.orElseThrow(() -> 
				new ClienteNaoEncontradoException("Nenhum usuário encontrado com nome " + nome));
	}
	
	public void deletaCliente(Long id) {
		clienteRepository.deleteClienteById(id);
	}
}
