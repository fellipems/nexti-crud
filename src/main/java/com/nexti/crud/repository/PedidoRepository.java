package com.nexti.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexti.crud.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
