package com.exe212.tutormind.exception;


import com.exe212.tutormind.Model.Message;

public class InvalidateException extends Exception {
    @Override
    public String getMessage(){
        return Message.msgInvalidCredential;
    }
}
