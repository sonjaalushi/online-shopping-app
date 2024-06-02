package com.sda.online_shopping_app.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message){
        super(message);
    }

}
