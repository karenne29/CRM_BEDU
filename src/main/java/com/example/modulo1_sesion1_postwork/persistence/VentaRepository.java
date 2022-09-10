package com.example.modulo1_sesion1_postwork.persistence;

import com.example.modulo1_sesion1_postwork.persistence.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
