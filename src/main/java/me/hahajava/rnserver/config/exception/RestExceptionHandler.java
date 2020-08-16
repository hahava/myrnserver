package me.hahajava.rnserver.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static me.hahajava.rnserver.config.exception.ExceptionMessage.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionResponse(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(DEFAULT_ERROR.message);
    }

    /**
     * 로그인 실패시 발생하는 예외를 처리
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<String> noLoginData(InternalAuthenticationServiceException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(FORBIDDEN)
                .body(NO_LOGIN_DATA.message);
    }

    /**
     * http 요청시 매개변수가 올바르지 않은 경우
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> wrongParameterResponse(NoSuchElementException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(BAD_REQUEST_PARAMETER.message);
    }
}
