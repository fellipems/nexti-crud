package com.nexti.crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexti.crud.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findProdutoByNome(String nome);

	Optional<Produto> deleteProdutoById(Long id);

}
