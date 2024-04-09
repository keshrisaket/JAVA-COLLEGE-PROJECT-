package com.example.javacollegeproject.exception;

public class StudentNotFound extends RuntimeException{

    public StudentNotFound(String message){
        super(message);
    }

    public StudentNotFound(String message,Throwable cause){
        super(message, cause);
    }
}
