package github.mathlazaro.desafioitau.shared.infra.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleAllUncaughtException(Exception e) {
        logger.error("Unknown error occurred", e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public void handleBadRequestExceptions(Exception e) {
        logger.warn("Bad request error occurred");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("Malformed JSON request", e);
    }

}
