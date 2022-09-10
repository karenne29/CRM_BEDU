package com.example.modulo1_sesion1_postwork.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "CLIENTES")
@Entity
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre")

    private String nombre;

    @Column(name = "correo_contacto", nullable = false)
    private String correoContacto;

    @Column(name = "numero_empleados")
    private String numeroEmpleados;

    @Column(name = "direccion")
    private String direccion;




}