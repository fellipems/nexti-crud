package com.nexti.crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexti.crud.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findProdutoByNome(String nome);

	Optional<Produto> deleteProdutoById(Long id);

}
