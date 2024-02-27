package com.jarkial.users.model.entity.ctg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CTG_CATALOGO")
@NoArgsConstructor
public class CtgCatalogo {
    
    @Id
    @Column(name = "ctg_cat_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdCtgCatalogoGenerator")
    @SequenceGenerator(allocationSize = 1, name = "IdCtgCatalogoGenerator", sequenceName = "ctg_cat_id_seq")*/
    @Getter @Setter
    private Long ctgCatalogoId;

    @Column(name = "ctg_cat_activo", nullable=false, length=1)
    @Getter @Setter
    private String ctgCatalogoActivo;

    @Column(name = "ctg_cat_descripcion", length=200)
    @Getter @Setter
    private String ctgCatalogoDescripcion;

    @Column(name = "ctg_cat_nombre", nullable=false, length=125)
    @Getter @Setter
    private String ctgCatalogoNombre;

    @ManyToOne
    @JoinColumn(name = "ctg_cat_id_padre", nullable= true)
    @Getter @Setter
    private CtgCatalogo ctgCatalogoPadre;

    @Column(name = "ctg_cat_sujeto_operacion", length=1)
    @Getter @Setter
    private String ctgCatalogoSujetoOperacion;

    @Column(name = "ctg_cat_homologacion", length=200)
    @Getter @Setter
    private String ctgCatalogoHomologacion;

    @Column(name = "ctg_cat_prioridad")
    @Getter @Setter
    private Integer ctgCatalogoPrioridad;

    @Column(name = "ctg_cat_auxiliar1", length=1)
    @Getter @Setter
    private String ctgCatalogoAuxiliar1;

    public CtgCatalogo(Long id){
        this.ctgCatalogoId = id;
    }
}
