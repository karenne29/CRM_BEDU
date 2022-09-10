package com.example.modulo1_sesion1_postwork.Controllers.Mappers;


import com.example.modulo1_sesion1_postwork.model.VisitaModel;
import com.example.modulo1_sesion1_postwork.persistence.entities.Visita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {

    Visita visitaModelToVisita(VisitaModel visitaModel);
    VisitaModel visitaToVisitaModel(Visita visita);
}
