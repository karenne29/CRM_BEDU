package com.example.modulo1_sesion1_postwork.Controllers;

import com.example.modulo1_sesion1_postwork.model.ClienteModel;
import com.example.modulo1_sesion1_postwork.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.KeyAgreementSpi;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> getCliente(@PathVariable Long clienteId){
        Optional<ClienteModel> clienteDb = clienteService.obtenCliente(clienteId);

        if (clienteDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.");
        }

        return ResponseEntity.ok(clienteDb.get());
    }

    @GetMapping("/getAllClientes")
    public ResponseEntity <List<ClienteModel>> getClientes(){

        return ResponseEntity.ok(clienteService.obtenClientes());
    }

    @PostMapping("/addCliente")
    public ResponseEntity<Void> creaCliente(@Valid @RequestBody ClienteModel cliente){
        ClienteModel clienteNuevo = clienteService.guardaCliente(cliente);
        return ResponseEntity.created(URI.create(String.valueOf(clienteNuevo.getId()))).build();
    }

    @PutMapping("/edit/{clienteId}")
    public ResponseEntity<String> actualizaCliente( @PathVariable Long clienteId,@Valid @RequestBody ClienteModel cliente){
//        if(clienteService.obtenCliente(clienteId).get()!=null){
//            cliente.setId(clienteId);
//            clienteService.actualizaCliente(cliente);
//            return ResponseEntity.status(201).body("Se actualizo la informacion del cliente");
//        }else{
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Id que ingresaste no existe");
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Id que ingresaste no existe");
//        }
        clienteService.actualizaCliente(cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<Void> eliminaCliente(@PathVariable Long clienteId){
        clienteService.eliminaCliente(clienteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
