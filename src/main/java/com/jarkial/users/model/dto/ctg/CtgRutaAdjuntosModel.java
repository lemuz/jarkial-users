package com.jarkial.users.model.dto.ctg;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.jarkial.users.model.entity.sgd.SgdUsuario;

import java.sql.Timestamp;

@Data
public class CtgRutaAdjuntosModel {

    @Getter @Setter
    private Long ctgRutaAdjuntosId;

    @Getter @Setter
    private String ctgRutaAdjuntosNombre;

    private String ctgRutaAdjuntosValor;

    @Getter @Setter
    private String ctgRutaAdjuntosMaxSize;

    @Getter @Setter
    private Timestamp ctgRutaAdjuntosFechaActualizacion;
    
    @Getter @Setter
    private Long sgdUsuario;

}
