/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.cj.callback.UsernameCallback;
import java.time.LocalDate;

/**
 *
 * @author Habib
 */
public class GuideViewModel {
    int orderId;
    String userName;
    String contactNo;
    String email;
    LocalDate date;

    public GuideViewModel(int orderId, String userName, String contactNo, String email, LocalDate date) {
        this.orderId = orderId;
        this.userName = userName;
        this.contactNo = contactNo;
        this.email = email;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
}
