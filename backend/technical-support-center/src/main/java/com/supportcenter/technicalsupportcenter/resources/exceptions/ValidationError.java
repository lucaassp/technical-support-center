package com.supportcenter.technicalsupportcenter.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldErrorMessage> errors = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldErrorMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldErrorMessage(fieldName, message));
    }
}
