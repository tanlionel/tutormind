package com.exe212.tutormind.exception;

import com.exe212.tutormind.Model.Message;

public class UserDoesNotHavePermission extends Exception{
    @Override
    public String getMessage(){
        return Message.msgInvalidPermission;
    }
}
