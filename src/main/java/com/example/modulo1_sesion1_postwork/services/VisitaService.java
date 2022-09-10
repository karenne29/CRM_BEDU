package com.example.modulo1_sesion1_postwork.services;

import com.example.modulo1_sesion1_postwork.Controllers.Mappers.ProductoMapper;
import com.example.modulo1_sesion1_postwork.Controllers.Mappers.VisitaMapper;
import com.example.modulo1_sesion1_postwork.model.VisitaModel;
import com.example.modulo1_sesion1_postwork.persistence.ProductoRepository;
import com.example.modulo1_sesion1_postwork.persistence.VisitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitaService {

    private final VisitaRepository repository;
    private final VisitaMapper mapper;

    public VisitaModel guardaVisita(VisitaModel etapaModel) {
        return mapper.visitaToVisitaModel(
                repository.save(mapper.visitaModelToVisita(etapaModel))
        );
    }

    public List<VisitaModel> obtenVisitas(){
        return repository.findAll().stream().map(cliente -> mapper.visitaToVisitaModel(cliente)).collect(Collectors.toList());
    }

    public Optional<VisitaModel> obtenVisita(long idVisita) {
        return repository.findById(idVisita)
                .map(cliente -> Optional.of(mapper.visitaToVisitaModel(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaVisita(long idVisita){
        repository.deleteById(idVisita);
    }

    public VisitaModel actualizaVisita(VisitaModel etapaModel){
        return mapper.visitaToVisitaModel(
                repository.save(mapper.visitaModelToVisita(etapaModel))
        );
    }

    public long cuenteClientes(){
        return repository.count();
    }

}
