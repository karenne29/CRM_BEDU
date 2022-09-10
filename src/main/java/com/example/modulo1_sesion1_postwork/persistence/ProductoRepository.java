package com.example.modulo1_sesion1_postwork.persistence;

import com.example.modulo1_sesion1_postwork.persistence.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
