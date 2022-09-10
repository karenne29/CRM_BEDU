package com.example.modulo1_sesion1_postwork.Controllers.Mappers;


import com.example.modulo1_sesion1_postwork.model.ProductoModel;
import com.example.modulo1_sesion1_postwork.persistence.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoModel productoToProductoModel(Producto producto);
    Producto productoModelToProducto(ProductoModel productoModel);
}
