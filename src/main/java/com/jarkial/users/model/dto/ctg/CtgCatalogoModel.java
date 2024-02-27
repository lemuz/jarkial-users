package com.jarkial.users.model.dto.ctg;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CtgCatalogoModel {
    
    @Getter @Setter
    private Long ctgCatalogoId;

    @Getter @Setter
    private String ctgCatalogoActivo;

    @Getter @Setter
    private String ctgCatalogoDescripcion;

    @Getter @Setter
    private String ctgCatalogoNombre;

    @Getter @Setter
    private Long ctgCatalogoPadre;

    @Getter @Setter
    private String ctgCatalogoSujetoOperacion;

    @Getter @Setter
    private String ctgCatalogoHomologacion;

    @Getter @Setter
    private Integer ctgCatalogoPrioridad;

    @Getter @Setter
    private String ctgCatalogoAuxiliar1;
}
