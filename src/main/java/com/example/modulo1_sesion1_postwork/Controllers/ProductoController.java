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

    @GetMapping("/{productoId}")
    public ResponseEntity<ProductoModel> getProducto(@PathVariable Long productoId){
        Optional<ProductoModel> productoDb = productoService.obtenProducto(productoId);

        if (productoDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto especificado no existe.");
        }

        return ResponseEntity.ok(productoDb.get());
    }

    @GetMapping("/getAllProductos")
    public ResponseEntity <List<ProductoModel>> getProductos(){

        return ResponseEntity.ok(productoService.obtenProductos());
    }

    @PostMapping("/addProducto")
    public ResponseEntity<String> creaProducto(@Valid @RequestBody ProductoModel productoModel){
        productoService.guardaProducto(productoModel);
        return ResponseEntity.status(201).body("Se agrego el producto");
    }

    @PutMapping("/edit/{productoId}")
    public ResponseEntity<String> actualizaProducto(@Valid @PathVariable Long productoId, @RequestBody  ProductoModel productoModel){
        if(productoService.obtenProducto(productoId).get()!=null){
            productoModel.setId(productoId);
            productoService.actualizaProducto(productoModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion del producto");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{productoId}")
    public ResponseEntity<String> eliminaProducto(@Valid @PathVariable Long productoId){
        productoService.eliminaProducto(productoId);
        return ResponseEntity.status(201).body("Se elimino correctamente el producto");
    }
}
