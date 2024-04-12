package com.jarkial.users.controllers.ctg;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jarkial.users.controllers.AbstractBaseController;
import com.jarkial.users.controllers.CrudController;
import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.webservices.ctg.CtgCatalogoServiceWeb;

@RestController
@RequestMapping("/catalogs")
public class CtgCatalogoController extends AbstractBaseController implements CrudController<CtgCatalogoModel, Long> {

    @Autowired
    CtgCatalogoServiceWeb serviceWeb;

    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long entityId) throws Exception{
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.findById(entityId), HttpStatus.OK,
                "00000");
        return response;
    }

    @Override
    @PostMapping("/new/")
    public ResponseEntity<?> create(@RequestBody @NotBlank @Valid CtgCatalogoModel model, HttpServletRequest request) throws Exception{
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.update(0L, model), HttpStatus.OK,
                "00000");
        return response;
    }

    @Override
    @PutMapping("/modify/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long entityId, @RequestBody @NotBlank @Valid CtgCatalogoModel model, HttpServletRequest request) throws Exception{
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.update(entityId, model), HttpStatus.OK,
                "00000");
        return response;
    }

    @Override
    @DeleteMapping("/erase/")
    public ResponseEntity<?> deleteById(@RequestParam("id") Long entityId, HttpServletRequest request) throws Exception{
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.deleteById(entityId), HttpStatus.OK,
                "00000");
        return response;
    }

    @GetMapping("/search/")
    public ResponseEntity<?> findByCtgCatNombreAndCtgCatalogoPadreAsList(@RequestParam(name="ctgCatNombre", defaultValue = StringUtils.EMPTY) String ctgCatNombre, @RequestParam(name = "idPadre", defaultValue = "0") Long idPadre) throws Exception {
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.findByCtgCatNombreAndCtgCatalogoPadreAsList(ctgCatNombre, idPadre),
                HttpStatus.OK, "00000");
        return response;
    }

    @GetMapping("/search/page/")
    public ResponseEntity<?> findAllByCtgCatalogoPadreAsPage(@RequestParam(name="page", defaultValue = "0") Integer page, @RequestParam(name="property", defaultValue = "ctgCatNombre") String property, @RequestParam(name="itemsPerPage", defaultValue = "10") Integer itemsPerPage, @RequestParam(name = "idPadre", defaultValue = "0") Long idPadre) throws Exception {
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.findAllByCtgCatalogoPadreAsPage(idPadre, page, property, itemsPerPage),
                HttpStatus.OK, "00000");
        return response;
    }

    @InitBinder("create")
    public void InitBinderCreate(WebDataBinder binder) {
        binder.setDisallowedFields("ctgCatalogoId", "ctgCatalogoActivo", "ctgCatalogoDescripcion", "ctgCatalogoNombre",
                "ctgCatalogoPadre", "ctgCatalogoSujetoOperacion", "ctgCatalogoHomologacion", "ctgCatalogoPrioridad",
                "ctgCatalogoAuxiliar1");
    }

    @InitBinder("update")
    public void InitBinderUpdate(WebDataBinder binder) {
        binder.setDisallowedFields("ctgCatalogoId", "ctgCatalogoActivo", "ctgCatalogoDescripcion", "ctgCatalogoNombre",
                "ctgCatalogoPadre", "ctgCatalogoSujetoOperacion", "ctgCatalogoHomologacion", "ctgCatalogoPrioridad",
                "ctgCatalogoAuxiliar1");
    }
}
