package com.example.modulo1_sesion1_postwork.persistence;

import com.example.modulo1_sesion1_postwork.persistence.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
}
