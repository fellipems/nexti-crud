package com.nexti.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexti.crud.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("SELECT DISTINCT obj FROM Pedido obj JOIN FETCH obj.produtos"
			+ " ORDER BY obj.dataCompra ASC")
	List<Pedido> findPedidosComProdutos();

}
