package com.carpediem.skeleton.exception;

import com.carpediem.skeleton.model.error.ErrorCode;
import com.carpediem.skeleton.model.error.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException exception) {

        return new ResponseEntity<>(
                getErrorResponse(ErrorCode.NOT_FOUND, exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler({InvalidRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(RuntimeException exception) {

        return new ResponseEntity<>(
                getErrorResponse(ErrorCode.BAD_REQUEST, exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {

        return new ResponseEntity<>(
                getErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Unexpected system error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getErrorResponse(ErrorCode errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .code(errorCode)
                .message(errorMessage).build();
    }
}
