package com.nexti.crud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexti.crud.dto.PedidoDto;
import com.nexti.crud.entities.Pedido;
import com.nexti.crud.repositories.PedidoRepository;
import com.nexti.crud.repositories.ProdutoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	
	private final ProdutoRepository produtoRepository;	
	
	@Autowired
	public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
	}
	
	@Transactional(readOnly = true)
	public List<PedidoDto> todosPedidos() {
		List<Pedido> list = pedidoRepository.findPedidosComProdutos();
		return list.stream()
				.map(x -> new PedidoDto(x))
				.collect(Collectors.toList());
	}
	
//	@Transactional
//	public PedidoDto insert(PedidoDto dto) {
//		Pedido pedido = new Pedido(null, dto.g(),
//				dto.getLatitude(),
//				dto.getLongitude(),
//				Instant.now(),
//				OrderStatus.PENDING);	// pegar o DTO da requisição e salvar no banco. Instanciar um Order a partir do OrderDTO
//		for (ProdutoDto p : dto.getProdutos()) {	// associar o pedido com os produtos que vieram do DTO, antes de salvar no banco
//			Produto produto = produtoRepository.getOne(p.getId()); // instancia um produto só que nao vai no banco de dados, criando uma entidade gerenciada pelo JPA para que, quando formos salvar o pedido, ele também salve as associações de quais produtos estão nesse pedido;
//			pedido.getProdutos().add(produto);
//		} // for para percorrer todos os produtosDTO e associando cada produto ao pedido
//		pedido = pedidoRepository.save(pedido);	// o save retorna uma referência para o objeto salvo, por isso associamos uma variável para ele
//		return new PedidoDto(pedido);
//	}
}
