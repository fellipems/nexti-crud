package com.nexti.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexti.crud.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	void deleteClienteByid(Long id);
	

	List<Cliente> findClienteByNomeContaining(String nome);

	List<Cliente> findAllByOrderByNomeAsc();

}
