package com.nexti.crud.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nexti.crud.dto.ClienteDto;
import com.nexti.crud.entities.Cliente;
import com.nexti.crud.exceptions.ClienteNaoEncontradoException;
import com.nexti.crud.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
// end points e interação com BD, requests

    private final ClienteService clienteService;
    
    @Autowired
    public ClienteController(ClienteService clienteService) {
    	this.clienteService = clienteService;
    }
    
    @GetMapping
    public ResponseEntity<List<ClienteDto>> getTodosClientes() {
    	List<ClienteDto> clientes = clienteService.todosClientes();
    	return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    
    @GetMapping("/procurar/{nome}")
    public ResponseEntity<List<ClienteDto>> getClientePorNome(@PathVariable("nome") String nome) {
    	List<ClienteDto> clienteDto = clienteService.buscaCliente(nome);
    	if(!clienteDto.isEmpty()) {
    		return new ResponseEntity<>(clienteDto, HttpStatus.OK);
    	} else {
    		throw new ClienteNaoEncontradoException("Nenhum usuário encontrado com nome " + nome);
    	}
    }
    
	@PostMapping(path = "/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteDto> cadastraCliente(@RequestBody @Valid ClienteDto dto) {
		ClienteDto clienteDto = clienteService.cadastraCliente(dto);
		return new ResponseEntity<>(clienteDto, HttpStatus.CREATED);
	}
    
    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> atualizaCliente(@RequestBody Cliente cliente) {
    	Cliente clienteAtualizado = clienteService.atualizaCliente(cliente);
    	return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletaCliente(@PathVariable("id") Long id) {
    	clienteService.deletaCliente(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
