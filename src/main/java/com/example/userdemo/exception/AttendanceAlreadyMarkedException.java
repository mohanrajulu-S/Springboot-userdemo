package com.example.userdemo.exception;

public class AttendanceAlreadyMarkedException extends RuntimeException
{
    public AttendanceAlreadyMarkedException(String message) {
        super(message);
    }
}
