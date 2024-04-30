package com.septemarch.test.exceptions;


import com.septemarch.test.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;

@ControllerAdvice
public class ExceptionHandlerService {
    @ExceptionHandler(value = InvalidData.class )
    public ResponseEntity<Object> handleServiceException(final InvalidData ex, final WebRequest request) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorMessageDTO(ex.getMessage(), ex.getStatus()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public ResponseEntity<Object> handleServiceException(final MethodArgumentNotValidException ex) {

        final String messages = //"Wrong input data";
                ex.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .reduce((s1, s2) -> s1 + "; " + s2)
                        .orElse("We have an issue with creating error message");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDTO(messages, HttpStatus.BAD_REQUEST));
    }
//    @ExceptionHandler(value = LoginException.class)
//    public ResponseEntity<Object> handleServiceException(final LoginException ex, final WebRequest request){
//        return ResponseEntity
//                .status(ex.getStatus())
//                .body(new MessageDto(ex.getMessage(), ex.getStatus()));
//    }
//
//    @ExceptionHandler(value = NoSuchElementException.class)
//    public ResponseEntity<Object> handleServiceException(final NoSuchElementException ex, final WebRequest request){
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new MessageDto(ex.getMessage(),HttpStatus.NOT_FOUND));
//    }

}