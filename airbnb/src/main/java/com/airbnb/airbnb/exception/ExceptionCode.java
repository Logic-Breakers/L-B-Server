package com.airbnb.airbnb.exception;

import lombok.Getter;


public enum ExceptionCode {
    STAY_NOT_FOUND(404, "Stay not found"),
    IMAGE_NOT_FOUND(404, "Image not found"),
    IMAGE_LIMIT_EXCEEDED(404, "You can upload up to 10 images"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    LOGOUT(403,"Logged out account");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
