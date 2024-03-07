package org.example.testtaskmaksimgavriliuk.exceptions.handler;


import org.example.testtaskmaksimgavriliuk.exceptions.ErrorResponse;
import org.example.testtaskmaksimgavriliuk.exceptions.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse("NOT_FOUND", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MappingException.class)
    public ErrorResponse handleMappingException(MappingException exception) {
        return new ErrorResponse("MAPPING_ERROR", exception.getMessage());
    }

}