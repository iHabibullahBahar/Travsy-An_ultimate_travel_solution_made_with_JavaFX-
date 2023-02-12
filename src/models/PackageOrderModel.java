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
public class PackageOrderModel {
    int pkgOrderId;
    int pkgId;
    int userId;
    String userName;
    LocalDate orderDate;

    public PackageOrderModel(int pkgOrderId, int pkgId, int userId, String userName, LocalDate orderDate) {
        this.pkgOrderId = pkgOrderId;
        this.pkgId = pkgId;
        this.userId = userId;
        this.userName = userName;
        this.orderDate = orderDate;
    }

    public int getPkgOrderId() {
        return pkgOrderId;
    }

    public void setPkgOrderId(int pkgOrderId) {
        this.pkgOrderId = pkgOrderId;
    }

    public int getPkgId() {
        return pkgId;
    }

    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    
}
