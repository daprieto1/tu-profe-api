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
    
    //Authenticate exceptions
    public static final String BAD_CREDENTIALS = "The provided credentials are bad";
    public static final String BAD_ACCESS_TOKEN = "The provided access token is not valid";
    public static final String BAD_NONCE = "The provided nonce is not valid";
    
    //Logic Exceptions
    public static final String NOT_VALID_PARAMETER = "The given parameter is not valid.";   
    
    public static final String BAD_TEACHER_STATE = "The teacher had a bad state to perform this action.";
    public static final String GAME_RULES_ACCEPTED = "The game rules have been accepted.";
    public static final String TEACHER_ALREADY_EXISTS = "Este correo ya ha sido asociado a la cuenta de un profesor.";
    public static final String GAME_RULES_NOT_ACCEPTED = "El profesor no ha aceptado las reglas de juego.";
    public static final String EXAM_NOT_PASS = "El profesor no ha pasado el examen.";
    
    public static final String WRONG_INTERVIEW_CAPACITY = "La capacidad de la entrevista es erronea.";

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
