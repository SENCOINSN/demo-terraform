package com.sid.gl.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sid.gl.exceptions.ProductNotFoundException;
import com.sid.gl.models.Product;
import com.sid.gl.utils.Message;
import com.sid.gl.utils.MessageStatus;
import com.sid.gl.utils.Result;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public Result<List<Message>> handleProductNotFoundException(ProductNotFoundException ex) {
        return Result.createResultWithBody(HttpStatus.NOT_FOUND.value(),
        new Message(ex.getMessage(), MessageStatus.ERROR, null) , null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request,HttpServletResponse response,Exception ex) {
        log.error("Exception occurred", ex);
        return new ResponseEntity<>(Result.createResultWithoutBody(response.getStatus(),new Message(ex.getMessage(), MessageStatus.ERROR)),HttpStatus.BAD_REQUEST);
    }
    
}
