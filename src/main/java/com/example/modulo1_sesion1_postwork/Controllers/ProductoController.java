package com.example.modulo1_sesion1_postwork.Controllers;

import com.example.modulo1_sesion1_postwork.model.ProductoModel;
import com.example.modulo1_sesion1_postwork.model.ProductoModel;
import com.example.modulo1_sesion1_postwork.services.EtapaService;
import com.example.modulo1_sesion1_postwork.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/{etapaId}")
    public ResponseEntity<ProductoModel> getCliente(@PathVariable Long etapaId){
        Optional<ProductoModel> etapaDb = productoService.obtenProducto(etapaId);

        if (etapaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.");
        }

        return ResponseEntity.ok(etapaDb.get());
    }

    @GetMapping("/getAllClientes")
    public ResponseEntity <List<ProductoModel>> getClientes(){

        return ResponseEntity.ok(productoService.obtenProductos());
    }

    @PostMapping("/addCliente")
    public ResponseEntity<String> creaCliente(@Valid @RequestBody ProductoModel etapaModel){
        productoService.guardaProducto(etapaModel);
        return ResponseEntity.status(201).body("Se agrego el cliente");
    }

    @PutMapping("/edit/{clienteId}")
    public ResponseEntity<String> actualizaCliente(@Valid @PathVariable Long etapaId, @RequestBody  ProductoModel etapaModel){
        if(productoService.obtenProducto(etapaId).get()!=null){
            etapaModel.setId(etapaId);
            productoService.actualizaProducto(etapaModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion del cliente");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<String> eliminaCliente(@Valid @PathVariable Long clienteId){
        productoService.eliminaProducto(clienteId);
        return ResponseEntity.status(201).body("Se elimino correctamente el cliente");
    }
}
