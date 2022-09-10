package com.example.modulo1_sesion1_postwork.services;

import com.example.modulo1_sesion1_postwork.Controllers.Mappers.ClienteMapper;
import com.example.modulo1_sesion1_postwork.Controllers.Mappers.EtapaMapper;
import com.example.modulo1_sesion1_postwork.model.ClienteModel;
import com.example.modulo1_sesion1_postwork.model.EtapaModel;
import com.example.modulo1_sesion1_postwork.persistence.ClientesRepository;
import com.example.modulo1_sesion1_postwork.persistence.EtapaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtapaService {
    private final EtapaRepository repository;
    private final EtapaMapper mapper;

    public EtapaModel guardaEtapa(EtapaModel etapaModel) {
        return mapper.etapaToEtapaModel(
                repository.save(mapper.etapaModelToEtapa(etapaModel))
        );
    }

    public List<EtapaModel> obtenEtapas(){
        return repository.findAll().stream().map(cliente -> mapper.etapaToEtapaModel(cliente)).collect(Collectors.toList());
    }

    public Optional<EtapaModel> obtenEtapa(long idEtapa) {
        return repository.findById(idEtapa)
                .map(cliente -> Optional.of(mapper.etapaToEtapaModel(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaEtapa(long idEtapa){
        repository.deleteById(idEtapa);
    }

    public EtapaModel actualizaEtapa(EtapaModel etapaModel){
        return mapper.etapaToEtapaModel(
                repository.save(mapper.etapaModelToEtapa(etapaModel))
        );
    }

    public long cuenteClientes(){
        return repository.count();
    }
}
