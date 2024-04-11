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

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.controllers.AbstractBaseController;
import com.jarkial.users.controllers.CrudController;
import com.jarkial.users.model.dto.ctg.CtgTipoAgenciaModel;
import com.jarkial.users.webservices.ctg.CtgTipoAgenciaServiceWeb;

@RestController
@RequestMapping("tipo/agencia")
public class CtgTipoAgenciaController extends AbstractBaseController
        implements CrudController<CtgTipoAgenciaModel, Long> {

    @Autowired
    CtgTipoAgenciaServiceWeb serviceWeb;

    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.findById(entityId), HttpStatus.OK,
                "00000");
        MyUtils.finMetodo(start);
        return response;
    }

    @Override
    @PostMapping("/new/")
    public ResponseEntity<?> create(@RequestBody @NotBlank @Valid CtgTipoAgenciaModel model, HttpServletRequest request)
            throws Exception {
        long start = MyUtils.iniciaMetodo();
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.update(0L, model), HttpStatus.OK,
                "00000");
        MyUtils.finMetodo(start);
        return response;
    }

    @Override
    @PutMapping("/modify/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long entityId,
            @RequestBody @NotBlank @Valid CtgTipoAgenciaModel model, HttpServletRequest request) throws Exception {
        long start = MyUtils.iniciaMetodo();
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.update(entityId, model), HttpStatus.OK,
                "00000");
        MyUtils.finMetodo(start);
        return response;
    }

    @Override
    @DeleteMapping("/erase/")
    public ResponseEntity<?> delete(@RequestParam("id") Long entityId, HttpServletRequest request) throws Exception {
        long start = MyUtils.iniciaMetodo();
        ResponseEntity<?> response;
        response = generateSingleResponseWithCode(SUCCESS, serviceWeb.deleteById(entityId), HttpStatus.OK,
                "00000");
        MyUtils.finMetodo(start);
        return response;
    }

    @InitBinder("create")
    public void InitBinderCreate(WebDataBinder binder) {
        binder.setDisallowedFields("ctgTipoAgenciaId", "ctgTipoAgenciaActivo", "ctgTipoAgenciaDescripcion");
    }

    @InitBinder("update")
    public void InitBinderUpdate(WebDataBinder binder) {
        binder.setDisallowedFields("ctgTipoAgenciaId", "ctgTipoAgenciaActivo", "ctgTipoAgenciaDescripcion");
    }

}
