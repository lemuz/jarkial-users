package com.jarkial.users.model.entity.gst;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;

@Entity
@Table(name = "GST_DOCUMENTO_CARGADO")
public class GstDocumentoCargado {
    
    @Id
    @Column(name = "gst_dcar_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdGstDocumentoCargadoGenerator")
    @SequenceGenerator(allocationSize = 1, name = "IdGstDocumentoCargadoGenerator", sequenceName = "gst_dcar_id_seq")*/
    @Getter @Setter
    private Long gstDocumentoCargadoId;

    @Column(name = "gst_dcar_descripcion", nullable=false, length=100)
    @Getter @Setter
    private String gstDocumentoCargadoDescripcion;

    @Column(name = "gst_dcar_fecha", nullable=false, length=15)
    @Getter @Setter
    private String gstDocumentoCargadoFecha;

    @ManyToOne
    @JoinColumn(name = "ctg_cat_id")
    @Getter @Setter
    private CtgCatalogo gstDocumentoCargadoTipoDocumento;

    @Column(name = "gst_dcar_extencion", length=10)
    @Getter @Setter
    private String gstDocumentoCargadoExtencion;
}