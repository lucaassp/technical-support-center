package com.supportcenter.technicalsupportcenter.resources.exceptions;

import java.io.Serializable;

public class FieldErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldError;
    private String messege;

    public FieldErrorMessage() {
    }

    public FieldErrorMessage(String fieldError, String messege) {
        this.fieldError = fieldError;
        this.messege = messege;
    }

    public String getFieldError() {
        return fieldError;
    }

    public String getMessege() {
        return messege;
    }
}
