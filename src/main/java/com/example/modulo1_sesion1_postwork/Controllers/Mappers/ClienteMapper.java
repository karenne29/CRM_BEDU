package com.example.modulo1_sesion1_postwork.Controllers.Mappers;


import com.example.modulo1_sesion1_postwork.model.ClienteModel;
import com.example.modulo1_sesion1_postwork.persistence.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteModelToClienteEntity(ClienteModel clienteModel);

    ClienteModel clienteEntityToClienteModel(Cliente cliente);
}
