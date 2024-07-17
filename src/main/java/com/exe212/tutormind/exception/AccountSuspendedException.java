package com.exe212.tutormind.exception;


import com.exe212.tutormind.model.Message;

public class AccountSuspendedException extends Exception {
    @Override
    public String getMessage(){
        return Message.msgAccountSuspended;
    }
}
