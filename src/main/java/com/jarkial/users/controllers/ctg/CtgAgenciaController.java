package com.jarkial.users.controllers.ctg;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarkial.users.controllers.AbstractBaseController;
import com.jarkial.users.controllers.CrudController;
import com.jarkial.users.model.entity.ctg.CtgAgencia;

@RestController
@RequestMapping("agencia")
public class CtgAgenciaController extends AbstractBaseController implements CrudController<CtgAgencia, Long>{

    @Override
    @GetMapping
    public ResponseEntity<?> show(Long entityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(CtgAgencia model, HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(Long entityId, CtgAgencia model, HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> delete(Long entityId, HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    @InitBinder("create")
    public void InitBinderCreate(WebDataBinder binder){
        binder.setDisallowedFields("ctgAgenciaId", "ctgAgenciaActivo", "ctgAgenciaCodigo", "ctgAgenciaDescripcion", "ctgSubTipoAgencia", "ctgAgenciaAtiendeOtrasAgencias", "ctgAgenciaPoseeComite");
    }

    @InitBinder("update")
    public void InitBinderUpdate(WebDataBinder binder){
        binder.setDisallowedFields("ctgAgenciaId", "ctgAgenciaActivo", "ctgAgenciaCodigo", "ctgAgenciaDescripcion", "ctgSubTipoAgencia", "ctgAgenciaAtiendeOtrasAgencias", "ctgAgenciaPoseeComite");
    }
}
