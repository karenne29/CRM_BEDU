package com.example.modulo1_sesion1_postwork.persistence.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "ETAPAS")
@Entity
@NoArgsConstructor
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etapaId;

    private String nombre;

    @Column(unique = true, nullable = false)
    private Integer orden;
}
