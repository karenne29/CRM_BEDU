package com.example.modulo1_sesion1_postwork.Controllers;

import com.example.modulo1_sesion1_postwork.model.ClienteModel;
import com.example.modulo1_sesion1_postwork.model.EtapaModel;
import com.example.modulo1_sesion1_postwork.services.ClienteService;
import com.example.modulo1_sesion1_postwork.services.EtapaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etapa")
@RequiredArgsConstructor
public class EtapaController {

    private final EtapaService etapaService;

    @GetMapping("/{etapaId}")
    public ResponseEntity<EtapaModel> getCliente(@PathVariable Long etapaId){
        Optional<EtapaModel> etapaDb = etapaService.obtenEtapa(etapaId);

        if (etapaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.");
        }

        return ResponseEntity.ok(etapaDb.get());
    }

    @GetMapping("/getAllClientes")
    public ResponseEntity <List<EtapaModel>> getClientes(){

        return ResponseEntity.ok(etapaService.obtenEtapas());
    }

    @PostMapping("/addCliente")
    public ResponseEntity<String> creaCliente(@Valid @RequestBody EtapaModel etapaModel){
        etapaService.guardaEtapa(etapaModel);
        return ResponseEntity.status(201).body("Se agrego el cliente");
    }

    @PutMapping("/edit/{clienteId}")
    public ResponseEntity<String> actualizaCliente(@PathVariable Long etapaId,@Valid @RequestBody  EtapaModel etapaModel){
        if(etapaService.obtenEtapa(etapaId).get()!=null){
            etapaModel.setEtapaId(etapaId);
            etapaService.actualizaEtapa(etapaModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion del cliente");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<String> eliminaCliente(@PathVariable Long clienteId){
        etapaService.eliminaEtapa(clienteId);
        return ResponseEntity.status(201).body("Se elimino correctamente el cliente");
    }
}
