package com.example.modulo1_sesion1_postwork.Controllers.Mappers;


import com.example.modulo1_sesion1_postwork.model.EtapaModel;
import com.example.modulo1_sesion1_postwork.persistence.entities.Etapa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtapaMapper {

    EtapaModel etapaToEtapaModel(Etapa etapa);
    Etapa etapaModelToEtapa(EtapaModel etapaModel);

}
