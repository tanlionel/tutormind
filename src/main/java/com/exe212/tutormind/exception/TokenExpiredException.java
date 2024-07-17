package com.exe212.tutormind.exception;


import com.exe212.tutormind.model.Message;

public class TokenExpiredException extends Exception {
    @Override
    public String getMessage() {
        return Message.msgTokenExpired;
    }
}
