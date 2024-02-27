package com.jarkial.users.model.dto.ctg;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CtgTipoAgenciaModel {

    @Getter @Setter
    private Long ctgTipoAgenciaId;

    @Getter @Setter
    private String ctgTipoAgenciaActivo;

    @Getter @Setter
    private String ctgTipoAgenciaDescripcion;
    
}
