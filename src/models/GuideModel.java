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
public class GuideModel {
    int userId,guideId;
    String name;
    String placeOfService;
    int hourlyRate,dailyRate;
    String custom;
    double rating;
    String status;
    String userName;
    public GuideModel(int userId, int guideId, String name, String placeOfService, int hourlyRate, int dailyRate, double rating) {
        this.userId = userId;
        this.guideId = guideId;
        this.name = name;
        this.placeOfService = placeOfService;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.rating = rating;
        
        
    }
    public GuideModel(int userId,int guideId, String name, String placeOfService, int hourlyRate, int dailyRate, double rating,String status) {
        this.userId = userId;
        this.guideId = guideId;
        this.name = name;
        this.placeOfService = placeOfService;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.rating = rating;
        this.status = status;
    }
    
    public GuideModel(int userId,int guideId, String name, String placeOfService, int hourlyRate, int dailyRate, double rating,String status,String userName) {
        this.userId = userId;
        this.guideId = guideId;
        this.name = name;
        this.placeOfService = placeOfService;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.rating = rating;
        this.status = status;
        this.userName = userName;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfService() {
        return placeOfService;
    }

    public void setPlaceOfService(String placeOfService) {
        this.placeOfService = placeOfService;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
