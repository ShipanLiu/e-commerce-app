package com.shipan.ecommerce.service.exceptionHandler;

// 	@ControllerAdvice: Indicates that this class provides global exception handling. It acts as an interceptor for exceptions thrown across all @Controller classes in the application.
//  Propagation of Exceptions:
//	•	When an exception is thrown in a service layer (like CustomerService), it propagates up to the controller layer unless explicitly caught and handled within the service.
//	•	If the exception reaches the controller layer without being handled, the @ControllerAdvice can intercept it, even though the original exception occurred in the service layer.

import com.shipan.ecommerce.entity.exception.CustomerNotFoundException;
import com.shipan.ecommerce.entity.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * @RestControllerAdvice automatically applies @ResponseBody to all exception-handling methods,
 *   meaning the response will be converted directly to JSON (or XML, if configured).
 *
 * */
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handle the CustomerNotFoundException(For 404 not found status)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // Handle validation exceptions( e.g. @Valid in CustomerRequest)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach((error -> {
            if (error instanceof FieldError fieldError) {
                var argumentName = fieldError.getField();
                var msg = fieldError.getDefaultMessage();
                errors.put(argumentName, msg);
            }
        }));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));

    }

    // Handle other general exceptions


}
