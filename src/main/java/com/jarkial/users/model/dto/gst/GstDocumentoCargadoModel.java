package com.jarkial.users.model.dto.gst;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GstDocumentoCargadoModel {

    @Getter @Setter
    private Long gstDocumentoCargadoId;

    @Getter @Setter
    private String gstDocumentoCargadoDescripcion;

    @Getter @Setter
    private String gstDocumentoCargadoFecha;

    @Getter @Setter
    private Long gstDocumentoCargadoTipoDocumento;

    @Getter @Setter
    private String gstDocumentoCargadoExtencion;

    @Getter @Setter
    private Long sgdUsuario;
}