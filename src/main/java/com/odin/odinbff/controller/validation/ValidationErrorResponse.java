package com.odin.odinbff.controller.validation;

public class ValidationErrorResponse {
    private final String attribute;
    private final String message;
    private final String value;

    private final String label;

    public ValidationErrorResponse(final String attribute, final String value, final String message, final String label) {
        this.attribute = attribute;
        this.value = value;
        this.message = message;
        this.label = label;
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

    public String getLabel() {
        return label;
    }
}
