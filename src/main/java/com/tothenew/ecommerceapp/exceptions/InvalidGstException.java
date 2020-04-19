package com.tothenew.ecommerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidGstException extends RuntimeException {
    public InvalidGstException(String s) {
        super(s);
    }
}
