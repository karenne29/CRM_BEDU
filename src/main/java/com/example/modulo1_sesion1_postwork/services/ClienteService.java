package com.example.modulo1_sesion1_postwork.services;

import com.example.modulo1_sesion1_postwork.Controllers.Mappers.ClienteMapper;
import com.example.modulo1_sesion1_postwork.model.ClienteModel;
import com.example.modulo1_sesion1_postwork.persistence.ClientesRepository;
import com.example.modulo1_sesion1_postwork.persistence.entities.Cliente;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClientesRepository repository;
    private final ClienteMapper mapper;

    public ClienteModel guardaCliente(ClienteModel cliente) {
        return mapper.clienteEntityToClienteModel(
                repository.save(mapper.clienteModelToClienteEntity(cliente))
        );
    }

    public List<ClienteModel> obtenClientes(){
        return repository.findAll().stream().map(cliente -> mapper.clienteEntityToClienteModel(cliente)).collect(Collectors.toList());
    }

    public Optional<ClienteModel> obtenCliente(long idCliente) {
        return repository.findById(idCliente)
                .map(cliente -> Optional.of(mapper.clienteEntityToClienteModel(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaCliente(long idcliente){
        repository.deleteById(idcliente);
    }

    public ClienteModel actualizaCliente(ClienteModel cliente){
        return mapper.clienteEntityToClienteModel(
                repository.save(mapper.clienteModelToClienteEntity(cliente))
        );
    }

    public long cuenteClientes(){
        return repository.count();
    }
}


