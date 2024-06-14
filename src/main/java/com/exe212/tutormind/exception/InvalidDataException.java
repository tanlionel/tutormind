package com.exe212.tutormind.exception;

public class InvalidDataException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid data!";
    }

}
