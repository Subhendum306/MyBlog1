package com.myblog.myblog1.exception;

import com.myblog.myblog1.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest){

         ErrorDetails errorDetails =new ErrorDetails(e.getMessage(),new Date(),webRequest.getDescription(true));
         return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
     }
}

 /*The provided code is a Java class named GlobalExceptionHandler annotated with @ControllerAdvice.
 This class is intended to handle exceptions globally for all controllers in a Spring MVC application.
 Let's break down the code step by step:
@ControllerAdvice annotation:
 This annotation is used to indicate that the class provides global exception handling for Spring MVC controllers.
 It allows you to define global exception handlers that apply across all controllers in your application.
 Extending ResponseEntityExceptionHandler:
 The GlobalExceptionHandler class extends ResponseEntityExceptionHandler,
 which is a convenient base class provided by Spring MVC for handling exceptions and returning appropriate ResponseEntity objects.
@ExceptionHandler annotation:
 This annotation is used to declare a method as an exception handler for a specific type of exception.
 In this case, the method is annotated with @ExceptionHandler(ResourceNotFoundException.class), indicating that it handles ResourceNotFoundException exceptions
 handleResourceNotFoundException method:

  This method is responsible for handling ResourceNotFoundException exceptions.
  It takes two parameters: the first parameter is the exception itself (ResourceNotFoundException e), and the second parameter is a WebRequest object (WebRequest webRequest), which provides access to the request details.
  Inside the method, an ErrorDetails object is created to encapsulate details about the error.
  This includes the error message (e.getMessage()), the timestamp of when the error occurred (new Date()), and a description of the request (webRequest.getDescription(includeClientInfo: true)).
  Finally, a ResponseEntity object is created with the ErrorDetails object as the body and HttpStatus.INTERNAL_SERVER_ERROR as the status code.
  This indicates that an internal server error occurred while processing the request.
  Overall, this code demonstrates how to create a global exception handler using @ControllerAdvice and @ExceptionHandler annotations in Spring MVC.
  It allows for centralized handling of specific exceptions across all controllers in the application, providing consistent error responses to clients.*/
        