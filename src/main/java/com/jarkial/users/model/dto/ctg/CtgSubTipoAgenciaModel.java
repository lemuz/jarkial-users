package com.jarkial.users.model.dto.ctg;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CtgSubTipoAgenciaModel {
    
    @Getter @Setter
    private Long ctgSubTipoAgenciaId;

    @Getter @Setter
    private String ctgSubTipoAgenciaActivo;

    @Getter @Setter
    private String ctgSubTipoAgenciaDescripcion;

    @Getter @Setter
    private Long ctgTipoAgencia;
}