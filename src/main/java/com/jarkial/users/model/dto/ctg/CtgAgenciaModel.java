package com.jarkial.users.model.dto.ctg;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CtgAgenciaModel {
    
    @Getter @Setter
    private Long ctgAgenciaId;

    @Getter @Setter
    private String ctgAgenciaActivo;

    @Getter @Setter
    private String ctgAgenciaCodigo;

    @Getter @Setter
    private String ctgAgenciaDescripcion;

    @Getter @Setter
    private Long ctgSubTipoAgencia;

    @Getter @Setter
    private String ctgAgenciaAtiendeOtrasAgencias;

    @Getter @Setter
    private String ctgAgenciaPoseeComite;
}