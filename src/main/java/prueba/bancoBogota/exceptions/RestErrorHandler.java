/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.bancoBogota.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author danie
 */
@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(EmployeeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SecurityResponse processValidationError(EmployeeException ex) {
        
        System.out.println("###########");
        SecurityResponse response = new SecurityResponse(ex.getMessage());
        return response;
    }
}

