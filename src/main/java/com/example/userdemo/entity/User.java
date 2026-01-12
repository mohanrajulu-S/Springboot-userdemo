package com.example.userdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "user_mobile_number")
    private String userMobileNumber;

    public User()
    {

    }

//    public User(String userName, String userMail, String userMobileNumber)
//    {
//        this.userName = userName;
//        this.userMail = userMail;
//        this.userMobileNumber = userMobileNumber;
//    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", userName='" + userName + '\'' +
//                ", userMail='" + userMail + '\'' +
//                ", userMobileNumber='" + userMobileNumber + '\'' +
//                '}';
//    }

}
