package com.example.modulo1_sesion1_postwork.services;

import com.example.modulo1_sesion1_postwork.Controllers.Mappers.ProductoMapper;
import com.example.modulo1_sesion1_postwork.Controllers.Mappers.VentanaMapper;
import com.example.modulo1_sesion1_postwork.model.VentaModel;
import com.example.modulo1_sesion1_postwork.persistence.ProductoRepository;
import com.example.modulo1_sesion1_postwork.persistence.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository repository;
    private final VentanaMapper mapper;

    public VentaModel guardaVenta(VentaModel etapaModel) {
        return mapper.ventaToVentaModel(
                repository.save(mapper.ventaModelToVenta(etapaModel))
        );
    }

    public List<VentaModel> obtenVentas(){
        return repository.findAll().stream().map(cliente -> mapper.ventaToVentaModel(cliente)).collect(Collectors.toList());
    }

    public Optional<VentaModel> obtenVenta(long idVenta) {
        return repository.findById(idVenta)
                .map(cliente -> Optional.of(mapper.ventaToVentaModel(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaVenta(long idVenta){
        repository.deleteById(idVenta);
    }

    public VentaModel actualizaVenta(VentaModel etapaModel){
        return mapper.ventaToVentaModel(
                repository.save(mapper.ventaModelToVenta(etapaModel))
        );
    }

    public long cuenteClientes(){
        return repository.count();
    }

}
