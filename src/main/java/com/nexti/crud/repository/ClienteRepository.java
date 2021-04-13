package com.nexti.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexti.crud.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
