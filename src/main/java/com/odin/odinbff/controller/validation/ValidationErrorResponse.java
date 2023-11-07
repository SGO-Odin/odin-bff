package com.odin.odinbff.controller.validation;

public class ValidationErrorResponse {
    private final String attribute;
    private final String message;
    private final String value;

    public ValidationErrorResponse(final String attribute, final String value, final String message) {
        this.attribute = attribute;
        this.value = value;
        this.message = message;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
