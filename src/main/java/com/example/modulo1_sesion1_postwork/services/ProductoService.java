package com.example.modulo1_sesion1_postwork.services;

import com.example.modulo1_sesion1_postwork.Controllers.Mappers.EtapaMapper;
import com.example.modulo1_sesion1_postwork.Controllers.Mappers.ProductoMapper;
import com.example.modulo1_sesion1_postwork.model.ProductoModel;
import com.example.modulo1_sesion1_postwork.persistence.EtapaRepository;
import com.example.modulo1_sesion1_postwork.persistence.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoModel guardaProducto(ProductoModel etapaModel) {
        return mapper.productoToProductoModel(
                repository.save(mapper.productoModelToProducto(etapaModel))
        );
    }

    public List<ProductoModel> obtenProductos(){
        return repository.findAll().stream().map(cliente -> mapper.productoToProductoModel(cliente)).collect(Collectors.toList());
    }

    public Optional<ProductoModel> obtenProducto(long idEtapa) {
        return repository.findById(idEtapa)
                .map(cliente -> Optional.of(mapper.productoToProductoModel(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaProducto(long idEtapa){
        repository.deleteById(idEtapa);
    }

    public ProductoModel actualizaProducto(ProductoModel etapaModel){
        return mapper.productoToProductoModel(
                repository.save(mapper.productoModelToProducto(etapaModel))
        );
    }

    public long cuenteProductos(){
        return repository.count();
    }

}
