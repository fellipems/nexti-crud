package com.nexti.crud.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexti.crud.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	void deleteClienteByid(Long id);

	Optional<List<Cliente>> findClienteByNomeContaining(String nome);	// pode retornar ou não um cliente

}
