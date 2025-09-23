package com.arstialmq.javaweb.controlleradvice;

import com.arstialmq.javaweb.beans.ErrorResponsDTO;
import com.arstialmq.javaweb.customexception.FieldRequiredException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {



//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<String, Object>();
//        body.put("timestamp", LocalDate.now());
//        body.put("status", status.value());
//
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(ArithmeticException e, WebRequest request) {
        ErrorResponsDTO error = new ErrorResponsDTO();
        error.setError(e.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Số nguyên ko chia được cho 0");
        error.setDetails(details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequiredException(FieldRequiredException e, WebRequest request) {
        ErrorResponsDTO errorResponsDTO = new ErrorResponsDTO();
        errorResponsDTO.setError(e.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Check lai name or numberofbasement di vi bi null");
        errorResponsDTO.setDetails(details);
        return new ResponseEntity<>(errorResponsDTO, HttpStatus.BAD_GATEWAY);
    }
}
