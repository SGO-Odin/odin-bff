package com.odin.odinbff.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private final MessageSource messageSource;

    private final static String PREFIX_LABEL_ATTRIBUTE = "label.attribute.";

    public ValidationExceptionHandler(@Autowired final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorResponse> validationBindException(BindException validationBindException) {

        List<ValidationErrorResponse> errors = new ArrayList<>();
        validationBindException.getFieldErrors().forEach( e -> {
            errors.add(new ValidationErrorResponse(
                    e.getField(),
                    String.valueOf(e.getRejectedValue()),
                    this.messageSource.getMessage(e, LocaleContextHolder.getLocale()),
                    this.messageSource.getMessage(PREFIX_LABEL_ATTRIBUTE + e.getField(), null, "{" + PREFIX_LABEL_ATTRIBUTE + e.getField() + "}", Locale.getDefault())
            ));
        });

        return errors;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return "Valor" + exception.getMostSpecificCause().getMessage();
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> responseStatusException(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(responseStatusException.getStatusCode()).body(responseStatusException.getReason());
    }
}
