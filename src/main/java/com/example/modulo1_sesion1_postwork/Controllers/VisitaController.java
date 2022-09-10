package com.example.modulo1_sesion1_postwork.Controllers;


import com.example.modulo1_sesion1_postwork.model.VisitaModel;
import com.example.modulo1_sesion1_postwork.model.VisitaModel;
import com.example.modulo1_sesion1_postwork.services.VisitaService;
import com.example.modulo1_sesion1_postwork.services.VisitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("visita")
@RequiredArgsConstructor
public class VisitaController {

    private final VisitaService visitaService;

    @GetMapping("/{visitaId}")
    public ResponseEntity<VisitaModel> getVisita(@PathVariable Long visitaId){
        Optional<VisitaModel> visitaDb = visitaService.obtenVisita(visitaId);

        if (visitaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente especificado no existe.");
        }

        return ResponseEntity.ok(visitaDb.get());
    }

    @GetMapping("/getAllVisitas")
    public ResponseEntity <List<VisitaModel>> getVisitas(){

        return ResponseEntity.ok(visitaService.obtenVisitas());
    }

    @PostMapping("/addVisita")
    public ResponseEntity<String> creaVisita(@Valid @RequestBody VisitaModel visitaModel){
        visitaService.guardaVisita(visitaModel);
        return ResponseEntity.status(201).body("Se agrego el cliente");
    }

    @PutMapping("/edit/{clienteId}")
    public ResponseEntity<String> actualizaVisita(@Valid @PathVariable Long visitaId, @RequestBody  VisitaModel visitaModel){
        if(visitaService.obtenVisita(visitaId).get()!=null){
            visitaModel.setId(visitaId);
            visitaService.actualizaVisita(visitaModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion del cliente");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<String> eliminaVisita(@Valid @PathVariable Long clienteId){
        visitaService.eliminaVisita(clienteId);
        return ResponseEntity.status(201).body("Se elimino correctamente el cliente");
    }
}
