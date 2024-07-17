package com.exe212.tutormind.exception;


import com.exe212.tutormind.model.Message;

public class InvalidActionException extends Exception{

    @Override
    public String getMessage(){
        return Message.msgInvalidAction;
    }

}
