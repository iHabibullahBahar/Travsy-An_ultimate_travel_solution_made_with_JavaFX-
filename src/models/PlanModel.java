/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author Habib
 */
public class PlanModel {
    int userId=0 ;
    int planId=0;
    String planDescription;
    int importance;
    int process;
    LocalDate due_date;

    public PlanModel(int userId, int planId, String planDescription, int importance, int process, LocalDate due_date) {
        this.userId = userId;
        this.planId = planId;
        this.planDescription = planDescription;
        this.importance = importance;
        this.process = process;
        this.due_date = due_date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    } 
}
