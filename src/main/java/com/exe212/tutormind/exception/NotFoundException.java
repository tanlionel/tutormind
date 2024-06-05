package com.exe212.tutormind.exception;

public class NotFoundException extends Exception{
    @Override
    public String getMessage(){
        return "Not found!";
    }
}