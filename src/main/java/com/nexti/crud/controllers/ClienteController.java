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

import com.nexti.crud.entities.Cliente;
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
    
    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> getTodosClientes() {
    	List<Cliente> clientes = clienteService.todosClientes();
    	return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    
    @GetMapping("/procurar/{nome}")
    public ResponseEntity<Cliente> getClientePorNome(@PathVariable("nome") String nome) {
    	Cliente cliente = clienteService.buscaCliente(nome);
    	return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    
	@PostMapping(path = "/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> cadastraCliente(@RequestBody @Valid Cliente cliente) {
		Cliente novoCliente = clienteService.adicionaCliente(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}
    
    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> atualizaCliente(@RequestBody Cliente cliente) {
    	Cliente clienteAtualizado = clienteService.atualizaCliente(cliente);
    	return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<?> deletaCliente(@PathVariable("id") Long id) {
    	clienteService.deletaCliente(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
