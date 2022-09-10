package com.example.modulo1_sesion1_postwork.persistence;

import com.example.modulo1_sesion1_postwork.persistence.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
}
