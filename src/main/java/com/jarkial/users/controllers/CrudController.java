package com.jarkial.users.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface CrudController<CLASS, ID> {

    ResponseEntity<?> findById(ID entityId) throws Exception;

    ResponseEntity<?> create(CLASS model, HttpServletRequest request) throws Exception;

    ResponseEntity<?> update(ID entityId, CLASS model, HttpServletRequest request) throws Exception;

    ResponseEntity<?> deleteById(ID entityId, HttpServletRequest request) throws Exception;
    
}
