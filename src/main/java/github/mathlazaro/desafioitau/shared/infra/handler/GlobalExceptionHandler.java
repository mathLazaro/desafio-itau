package github.mathlazaro.desafioitau.shared.infra.handler;

import github.mathlazaro.desafioitau.shared.adapter.dto.StandardResponse;
import github.mathlazaro.desafioitau.shared.adapter.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardResponse handleAllUncaughtException(Exception e) {
        logger.error("Unknown error occurred", e);
        return new StandardResponse(
                "Unexpected error",
                null,
                null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public StandardResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn("Validation error: {}", e.getMessage());
        Map<String, List<String>> errors = new HashMap<>();

        e.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.computeIfAbsent(fieldError.getField(), key -> new java.util.ArrayList<>())
                                .add(fieldError.getDefaultMessage())
                );

        return new StandardResponse(
                "Validation failed",
                null,
                errors
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public StandardResponse handleBadRequestExceptions(IllegalArgumentException e) {
        logger.warn("Bad request error occurred: {}", e.getMessage());
        return new StandardResponse(
                e.getMessage(),
                null,
                null
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StandardResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.warn("Resource not found: {}", e.getMessage());
        return new StandardResponse(
                e.getMessage(),
                null,
                null
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("Malformed JSON request", e);
        return new StandardResponse(
                "Malformed JSON request",
                null,
                null
        );
    }


}
