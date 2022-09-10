package com.example.modulo1_sesion1_postwork.Controllers.Mappers;


import com.example.modulo1_sesion1_postwork.model.VentaModel;
import com.example.modulo1_sesion1_postwork.persistence.entities.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentanaMapper {

    Venta ventaModelToVenta(VentaModel venta);
    VentaModel ventaToVentaModel(Venta venta);
}
