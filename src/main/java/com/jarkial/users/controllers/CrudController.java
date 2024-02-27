package com.jarkial.users.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface CrudController<CLASS, ID> {

    ResponseEntity<?> show(ID entityId) throws Exception;

    ResponseEntity<?> create(CLASS model, HttpServletRequest request) throws Exception;

    ResponseEntity<?> update(ID entityId, CLASS model, HttpServletRequest request) throws Exception;

    ResponseEntity<?> delete(ID entityId, HttpServletRequest request) throws Exception;
    
}
