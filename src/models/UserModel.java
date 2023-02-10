/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author Habib
 */
public class UserModel {
    int userId;
    String userName;
    String fullName;
    String email;
    String ActivityStatus;

    public UserModel(int userId, String userName, String fullName, String email, String ActivityStatus) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.ActivityStatus = ActivityStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivityStatus() {
        return ActivityStatus;
    }

    public void setActivityStatus(String ActivityStatus) {
        this.ActivityStatus = ActivityStatus;
    }
    
    
    
}
