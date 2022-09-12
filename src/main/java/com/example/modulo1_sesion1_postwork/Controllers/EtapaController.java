package com.example.modulo1_sesion1_postwork.Controllers;

import com.example.modulo1_sesion1_postwork.model.EtapaModel;
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
    public ResponseEntity<EtapaModel> getEtapa(@PathVariable Long etapaId){
        Optional<EtapaModel> etapaDb = etapaService.obtenEtapa(etapaId);

        if (etapaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La etapa especificado no existe.");
        }

        return ResponseEntity.ok(etapaDb.get());
    }

    @GetMapping("/getAllEtapas")
    public ResponseEntity <List<EtapaModel>> getEtapas(){

        return ResponseEntity.ok(etapaService.obtenEtapas());
    }

    @PostMapping("/addEtapa")
    public ResponseEntity<String> creaEtapa(@Valid @RequestBody EtapaModel etapaModel){
        etapaService.guardaEtapa(etapaModel);
        return ResponseEntity.status(201).body("Se agrego la etapa");
    }

    @PutMapping("/edit/{etapaId}")
    public ResponseEntity<String> actualizaEtapa(@Valid @PathVariable Long etapaId, @RequestBody  EtapaModel etapaModel){
        if(etapaService.obtenEtapa(etapaId).get()!=null){
            etapaModel.setEtapaId(etapaId);
            etapaService.actualizaEtapa(etapaModel);
            return ResponseEntity.status(201).body("Se actualizo la informacion de la etapa");
        }else{
            return ResponseEntity.status(201).body("El Id que ingresaste no existe");
        }

    }

    @DeleteMapping("/delete/{etapaId}")
    public ResponseEntity<String> eliminaEtapa(@Valid @PathVariable Long etapaId){
        etapaService.eliminaEtapa(etapaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
