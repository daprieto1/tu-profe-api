/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api;

/**
 *
 * @author diego
 */
public class TuProfeAPIException extends RuntimeException {
    
    //General Exceptions
    public static final String NULL_PARAM = "The parameter could not be null.";
    public static final String EMPTY_PARAM = "The list is empty";
    
    //Persistance Exceptions
    public static final String NOT_FIND_ENTITY = "The entity does not exists.";
    
    //Logic Exceptions
    public static final String NOT_VALID_PARAMETER = "The given parameter is not valid.";   
    
    public static final String BAD_TEACHER_STATE = "The teacher had a bad state to perform this action.";

    public TuProfeAPIException() {
        super();
    }

    public TuProfeAPIException(String message) {
        super(message);
    }

    public TuProfeAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public TuProfeAPIException(Throwable cause) {
        super(cause);
    }

}
