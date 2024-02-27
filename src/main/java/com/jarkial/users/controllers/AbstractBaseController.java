package com.jarkial.users.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.OutPageResponse;
import com.jarkial.users.model.dto.OutResponse;
import com.jarkial.users.model.exceptions.MyServiceException;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestControllerAdvice
public class AbstractBaseController {
 
    private final Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);

    public static final String SUCCESS = "Exito";
    public static final String FAILURE = "Fallo";
    public static final String MESSAGE = "mensaje";

    protected String getMessage(String code){
        try {
            return code + " Causado Por: " + this.getPrincipal().getUsername();
        } catch (Exception e) {
            return code + " Causado Por: Sin autenticacion";
        }
    }

    protected UserDetails getPrincipal(){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof UserDetails) return (UserDetails) obj;
        else return null;
    }

    protected ResponseEntity<?> generateSingleResponseWithCode(String message, Object content, HttpStatus status, String codigo){
        OutResponse responseEntity = new OutResponse();
        responseEntity.setContent(content);
        responseEntity.setMessage(message);
        responseEntity.setCode(codigo);
        responseEntity.setErrors(null);
        return new ResponseEntity<Object>(responseEntity, status);
    }

    protected ResponseEntity<?> generateSingleResponseWithHeaders(Object content, HttpHeaders responseHeaders, HttpStatus status){
        OutResponse responseEntity = new OutResponse();
        responseEntity.setContent(content);
        return new ResponseEntity<Object>(responseEntity, responseHeaders, status);
    }

    protected ResponseEntity<?> generateErrorResponse(Exception exception, String message, HttpStatus status){
        OutResponse responseEntity = new OutResponse();
        responseEntity.setMessage(message);
        responseEntity.setErrors((exception!=null)? exception.getMessage() : "Desconocido");
        return new ResponseEntity<Object>(responseEntity, status);
    }

    protected ResponseEntity<?> generateErrorResponseWithCode(Exception exception, String message, HttpStatus status, String code){
        OutResponse responseEntity = new OutResponse();
        responseEntity.setMessage(message);
        responseEntity.setCode(code);
        responseEntity.setErrors((exception!=null)? exception.getMessage() : "Desconocido");
        responseEntity.setContent(MyUtils.getStackTrace(exception));
        return new ResponseEntity<Object>(responseEntity, status);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleBadRequestException(Exception exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE, HttpStatus.INTERNAL_SERVER_ERROR, "10000");
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleDataIntegrityViolationException(Exception exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage(" Error controller: integridad de datos violada"), HttpStatus.BAD_REQUEST, "00600");
    }

    @ResponseBody
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleInvalidDataAccessResourceUsageException(Exception exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage(" Error controller: informacion de acceso de uso de recursos invalido"), HttpStatus.BAD_REQUEST, "00600");
    }

    @ResponseBody
    @ExceptionHandler(IOException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleIOException(Exception exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage(" Error controller: error IO"), HttpStatus.BAD_REQUEST, "00600");
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleConstraintViolationException(Exception exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage(" Error controller: integridad de referencia violada"), HttpStatus.BAD_REQUEST, "00600");
    }

    @ResponseBody
    @ExceptionHandler(MyServiceException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleMyServiceException(Exception exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage("Error controller: excepcion personalizada"), HttpStatus.BAD_REQUEST, "00600");
    }

    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage("Archivo demasiado grande!"), HttpStatus.BAD_REQUEST, "00600");
    }

    @ExceptionHandler
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handle(HttpMessageNotReadableException exception){
        if(exception != null) exception.printStackTrace();
        return generateErrorResponseWithCode(exception, FAILURE + this.getMessage(" Error controller: error Mensaje no leijible"), HttpStatus.BAD_REQUEST, "00600");
    }

    protected static String getClientIpAddress(HttpServletRequest request){
        String xForwardedHeader = request.getHeader("X0Forwarded-For");
        if(xForwardedHeader == null)
            return request.getRemoteAddr();
        else
            return new StringTokenizer(xForwardedHeader, ",").nextToken().trim();
    }

    protected void serializeString(String cadena, HttpServletResponse response) throws IOException{
        response.setContentType("text/plain");
        response.getOutputStream().write(cadena.getBytes(StandardCharsets.UTF_8));
    }

    protected List<String> getNameAnnotatedFields(Object entity, Class jsonViewClass){
        List<String> camposAnotados = new ArrayList<>();
        Field[] campos = entity.getClass().getDeclaredFields();
        for(Field campo: campos){
            if(!campo.isAnnotationPresent(JsonView.class)){
                continue;
            }
            JsonView anotacion = campo.getAnnotation(JsonView.class);
            if(Arrays.asList(anotacion.value()).contains(jsonViewClass)){
                camposAnotados.add(campo.getName());
            }
        }
        return camposAnotados;
    }

    protected ResponseEntity<?> generatePageableResponse(Page<?> page, HttpStatus status){
        OutPageResponse responseEntity = new OutPageResponse();
        if(page != null){
            responseEntity.setCode("000000");
            responseEntity.setContent(page.getContent());
            responseEntity.setSize(page.getSize());
            responseEntity.setTotalCount(page.getTotalElements());
            responseEntity.setTotalPages(page.getTotalPages());
            responseEntity.setPageNumber(page.getNumber());
        }else{
            responseEntity.setCode("000001");
            responseEntity.setMessage("Pagina no existente");
        }
        return new ResponseEntity<Object>(responseEntity, status);
    }

    protected String getFecha(){
        String timestamp = MyUtilsConstant.dateFormatAsYYYYMMDDHHMMSS.format(Calendar.getInstance().getTime());
        return timestamp;
    }
}