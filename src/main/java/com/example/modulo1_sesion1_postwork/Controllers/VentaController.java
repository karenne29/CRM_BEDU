package com.example.modulo1_sesion1_postwork.Controllers;


import com.example.modulo1_sesion1_postwork.model.VentaModel;
import com.example.modulo1_sesion1_postwork.services.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping("/{ventaId}")
    public ResponseEntity<VentaModel> getVenta(@PathVariable Long ventaId){
        Optional<VentaModel> ventaDb = ventaService.obtenVenta(ventaId);

        if (ventaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El venta especificado no existe.");
        }

        return ResponseEntity.ok(ventaDb.get());
    }

    @GetMapping("/getAllVentas")
    public ResponseEntity <List<VentaModel>> getVentas(){

        return ResponseEntity.ok(ventaService.obtenVentas());
    }

    @PostMapping("/addVenta")
    public ResponseEntity<String> creaVenta(@Valid @RequestBody VentaModel ventaModel){
        ventaService.guardaVenta(ventaModel);
        return ResponseEntity.status(201).body("Se agrego el venta");
    }

    @PutMapping("/edit/{ventaId}")
    public ResponseEntity<String> actualizaVenta(@PathVariable Long ventaId,@Valid @RequestBody  VentaModel ventaModel){
        if(ventaService.obtenVenta(ventaId).get()!=null){
            ventaModel.setVentaId(ventaId);
            ventaService.actualizaVenta(ventaModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion del venta");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{ventaId}")
    public ResponseEntity<String> eliminaVenta(@PathVariable Long ventaId){
        ventaService.eliminaVenta(ventaId);
        return ResponseEntity.status(201).body("Se elimino correctamente el venta");
    }
}
