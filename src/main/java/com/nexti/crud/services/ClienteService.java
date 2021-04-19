package com.nexti.crud.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexti.crud.dto.ClienteDto;
import com.nexti.crud.entities.Cliente;
import com.nexti.crud.repositories.ClienteRepository;
import com.nexti.crud.repositories.PedidoRepository;

@Service
public class ClienteService {
// lógica de negócio
	
	private final ClienteRepository clienteRepository;
	
	private final PedidoRepository pedidoRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
	}
	
	@Transactional
	public ClienteDto cadastraCliente(ClienteDto clienteDto) {
		Cliente cliente = new Cliente(clienteDto.getId(), 
				clienteDto.getNome(), 
				clienteDto.getCpf(), 
				clienteDto.getDataNascimento());
		cliente = clienteRepository.save(cliente);
		return new ClienteDto(cliente);
	}
	
	public List<ClienteDto> todosClientes(){
		List<Cliente> clientes = clienteRepository.findAllByOrderByNomeAsc();
		return clientes.stream()
				.map(x -> new ClienteDto(x))
				.collect(Collectors.toList());
	}
	
	public Cliente atualizaCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<ClienteDto> buscaCliente(String nome) {
		
		List<Cliente> resultado = clienteRepository.findClienteByNomeContaining(nome);
		
		return resultado.stream()
				.map(x -> new ClienteDto(x))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deletaCliente(Long id) {
		clienteRepository.deleteClienteByid(id);
	}
}
