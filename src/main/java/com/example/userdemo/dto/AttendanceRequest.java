package com.example.userdemo.dto;

import com.example.userdemo.entity.AttendanceStatus;

public class AttendanceRequest
{
    private Integer userId;
    private AttendanceStatus status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
