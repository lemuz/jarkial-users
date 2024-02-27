package com.jarkial.users.controllers.ctg;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
    CtgCatalogoServiceWeb ctgCatalogoServiceWeb;

    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long entityId) throws Exception{
        // TODO Auto-generated method stub
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, ctgCatalogoServiceWeb.findById(entityId), HttpStatus.OK,
                "00000");
        return response;
    }

    @GetMapping("/search/")
    public ResponseEntity<?> listByPadre(@RequestParam("idPadre") Long idPadre) throws Exception {
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, ctgCatalogoServiceWeb.findAllByCtgCatalogoPadre(idPadre),
                HttpStatus.OK, "00000");
        return response;
    }

    @Override
    @PostMapping("/new/")
    public ResponseEntity<?> create(@RequestBody @NotBlank @Valid CtgCatalogoModel model, HttpServletRequest request) throws Exception{
        // TODO Auto-generated method stub
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, ctgCatalogoServiceWeb.update(0L, model), HttpStatus.OK,
                "00000");
        return response;
    }

    @Override
    @PutMapping("/modify/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long entityId, @RequestBody @NotBlank @Valid CtgCatalogoModel model, HttpServletRequest request) throws Exception{
        // TODO Auto-generated method stub
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, ctgCatalogoServiceWeb.update(entityId, model), HttpStatus.OK,
                "00000");
        return response;
    }

    @Override
    @DeleteMapping("/erase/")
    public ResponseEntity<?> delete(@RequestParam("id") Long entityId, HttpServletRequest request) throws Exception{
        // TODO Auto-generated method stub
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, ctgCatalogoServiceWeb.deleteById(entityId), HttpStatus.OK,
                "00000");
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
