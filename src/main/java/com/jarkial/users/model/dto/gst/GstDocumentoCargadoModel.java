package com.jarkial.users.model.dto.gst;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;

@Data
public class GstDocumentoCargadoModel {

    @Getter @Setter
    private Long gstDocumentoCargadoId;

    @Getter @Setter
    private String gstDocumentoCargadoDescripcion;

    @Getter @Setter
    private String gstDocumentoCargadoFecha;

    @Getter @Setter
    private CtgCatalogo gstDocumentoCargadoTipoDocumento;

    @Getter @Setter
    private String gstDocumentoCargadoExtencion;
}