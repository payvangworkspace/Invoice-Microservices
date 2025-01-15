package com.payvang.InvoiceRetrival.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.payvang.InvoiceRetrival.Models.APIResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	

@ExceptionHandler(InvoiceNotFoundException.class)
public ResponseEntity<APIResponse>handleResourceNotFoundException(InvoiceNotFoundException ex){
	
	String message=ex.getMessage();
	var response=APIResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
	return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	
																	

}
	
	
	

}
