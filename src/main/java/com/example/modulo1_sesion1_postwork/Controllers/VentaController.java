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
    public ResponseEntity<VentaModel> getCliente(@PathVariable Long ventaId){
        Optional<VentaModel> ventaDb = ventaService.obtenVenta(ventaId);

        if (ventaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.");
        }

        return ResponseEntity.ok(ventaDb.get());
    }

    @GetMapping("/getAllClientes")
    public ResponseEntity <List<VentaModel>> getClientes(){

        return ResponseEntity.ok(ventaService.obtenVentas());
    }

    @PostMapping("/addCliente")
    public ResponseEntity<String> creaCliente(@Valid @RequestBody VentaModel ventaModel){
        ventaService.guardaVenta(ventaModel);
        return ResponseEntity.status(201).body("Se agrego el cliente");
    }

    @PutMapping("/edit/{clienteId}")
    public ResponseEntity<String> actualizaCliente(@Valid @PathVariable Long ventaId, @RequestBody  VentaModel ventaModel){
        if(ventaService.obtenVenta(ventaId).get()!=null){
            ventaModel.setVentaId(ventaId);
            ventaService.actualizaVenta(ventaModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion del cliente");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<String> eliminaCliente(@Valid @PathVariable Long clienteId){
        ventaService.eliminaVenta(clienteId);
        return ResponseEntity.status(201).body("Se elimino correctamente el cliente");
    }
}
