package dev.duoplugins.website.api.error.handler;

import dev.duoplugins.website.api.error.data.Error;
import dev.duoplugins.website.api.token.exception.InvalidTokenException;
import dev.duoplugins.website.domain.client.exception.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oNospher
 **/
@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> tokenException(InvalidTokenException exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                status.value(),
                exception.getMessage(),
                null
        );

        return handleExceptionInternal(exception, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> clientException(ClientException exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                status.value(),
                exception.getMessage(),
                null
        );

        return handleExceptionInternal(exception, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error.Field> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            fields.add(
                    new Error.Field(
                            field,
                            message
                    )
            );
        });

        Error error = new Error(
                status.value(),
                "Some field is incorrect. Details below",
                fields
        );
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }
}
