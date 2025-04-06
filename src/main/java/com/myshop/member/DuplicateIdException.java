package com.myshop.member;

import lombok.Getter;

@Getter
public class DuplicateIdException extends RuntimeException {
    private String propertyName;

}
