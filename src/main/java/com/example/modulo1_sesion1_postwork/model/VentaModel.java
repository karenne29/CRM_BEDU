package com.example.modulo1_sesion1_postwork.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VentaModel {

    @PositiveOrZero(message = "El identificador de la venta no puede ser un número negativo")
    private long ventaId;

    @DecimalMin(value = "1.00", inclusive = true, message = "La venta debe ser de al menos 1.00")
    private float monto;

    @NotEmpty(message = "La venta debe tener por lo menos un producto.")
    private List<ProductoModel> productos;

    @NotNull(message = "La venta debe haberse realizado a algún cliente.")
    private ProductoModel cliente;

    @PastOrPresent(message = "La venta no puede ocurrir en el futuro.")
    private LocalDateTime fechaCreacion;

}
