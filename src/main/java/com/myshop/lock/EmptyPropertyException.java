package com.myshop.lock;

import lombok.Getter;

@Getter
public class EmptyPropertyException extends RuntimeException{
    public EmptyPropertyException(String s) {
    }

    private String propertyName;
}
