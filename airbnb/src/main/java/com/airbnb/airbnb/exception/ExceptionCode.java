package com.airbnb.airbnb.exception;

import lombok.Getter;


public enum ExceptionCode {
    STAY_NOT_FOUND(404, "Stay not found"),
    CATEGORY_NOT_FOUND(404, "Category not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
