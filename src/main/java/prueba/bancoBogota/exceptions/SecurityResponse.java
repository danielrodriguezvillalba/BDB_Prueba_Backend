/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.bancoBogota.exceptions;

/**
 *
 * @author danie
 */
public class SecurityResponse {

    private String error;

    public SecurityResponse() {

    }

    public SecurityResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}