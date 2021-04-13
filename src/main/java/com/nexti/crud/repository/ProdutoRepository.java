package com.nexti.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexti.crud.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
