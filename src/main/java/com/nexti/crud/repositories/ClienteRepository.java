package com.nexti.crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexti.crud.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	void deleteClienteById(Long id);

	Optional<Cliente> findClienteByNome(String nome);	// pode retornar ou não um cliente

}
