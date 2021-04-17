package com.nexti.crud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nexti.crud.dto.PedidoDto;
import com.nexti.crud.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private final PedidoService pedidoService;
	
	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoDto>> todosPedidos() {
		List<PedidoDto> pedidoList = pedidoService.todosPedidos();
		return ResponseEntity.ok().body(pedidoList);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDto> inserirPedidos(@RequestBody PedidoDto pedidoDto) {
		pedidoDto = pedidoService.inserirPedido(pedidoDto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(pedidoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(pedidoDto);
	}
}
