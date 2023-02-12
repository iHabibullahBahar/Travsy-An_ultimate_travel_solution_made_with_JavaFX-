package models;

import java.time.LocalDate;

public class GuideOrderModel{
    int orderId;
    int guideId;
    String guideName;
    int userId;
    String userName;
    LocalDate date;

    public GuideOrderModel(int orderId, int guideId, String guideName, int userId, String userName, LocalDate date) {
        this.orderId = orderId;
        this.guideId = guideId;
        this.guideName = guideName;
        this.userId = userId;
        this.userName = userName;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}