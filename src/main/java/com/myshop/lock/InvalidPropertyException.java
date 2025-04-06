package com.myshop.lock;

import lombok.Getter;

@Getter
public class InvalidPropertyException extends RuntimeException {

    private String propertyName;
    public InvalidPropertyException(String propertyName) {
        super("Invalid value for property: " + propertyName);
    }
}