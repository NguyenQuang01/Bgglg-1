package com.example.itspower.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.itspower.exception.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchElementException(
            ResourceNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getDetailsMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getErrorCode(),
                ex.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Object> handleDuplicateResourceException(
            DuplicateResourceException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getDetailsMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getErrorCode(),
                ex.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(ViolationBusinessException.class)
    public ResponseEntity<Object> handleViolationBusinessException(
            ViolationBusinessException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getDetailsMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getErrorCode(),
                ex.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(errorResponse, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.addAll(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList()));
        details.addAll(ex.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getObjectName(), error.getDefaultMessage()))
                .collect(Collectors.toList()));

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                METHOD_ARGUMENT_NOT_VALID.getResponseStatus(),
                METHOD_ARGUMENT_NOT_VALID.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(err, BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                METHOD_ARGUMENT_TYPE_MISMATCH.getResponseStatus(),
                METHOD_ARGUMENT_TYPE_MISMATCH.getCauseMsg(),
                details);

        return ResponseEntityBuilder.build(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(
            Exception ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                VIOLATE_VALIDATION_ARGUMENT.getResponseStatus(),
                VIOLATE_VALIDATION_ARGUMENT.getCauseMsg(),
                details);

        return ResponseEntityBuilder.build(errorResponse, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add("Parameter: " + ex.getParameterName() + " is missing");

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                MISSING_SERVLET_REQUEST_PARAMETER.getResponseStatus(),
                MISSING_SERVLET_REQUEST_PARAMETER.getCauseMsg(),
                details);

        return ResponseEntityBuilder.build(err, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                HTTP_MEDIA_TYPE_NOT_SUPPORTED.getResponseStatus(),
                HTTP_MEDIA_TYPE_NOT_SUPPORTED.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(err, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                NO_HANDLER_FOUND.getResponseStatus(),
                NO_HANDLER_FOUND.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(err, NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(
            Exception ex,
            WebRequest request) {
        ex.printStackTrace();
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                UNKNOWN_SERVER_ERROR.getResponseStatus(),
                UNKNOWN_SERVER_ERROR.getCauseMsg(),
                details);
        return ResponseEntityBuilder.build(err, HttpStatus.BAD_GATEWAY);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getCause().getMessage());

        ErrorResponse err = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                details);
        return ResponseEntityBuilder.build(err, status);
    }
}
