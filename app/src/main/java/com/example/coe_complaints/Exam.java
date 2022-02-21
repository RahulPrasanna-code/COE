package com.example.coe_complaints;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Exam extends RealmObject {
    @PrimaryKey
    private int _id;
    private String examName;
    private String examFee;
    private String examDate;
    private String lastDate;
    private String lastDateWithFine;
    private String eligibility;
    private boolean registered;

    public Exam() {
    }

    public Exam(String examName, String examFee, String examDate, String lastDate, String lastDateWithFine, String eligibility, boolean registered) {
        this.examName = examName;
        this.examFee = examFee;
        this.examDate = examDate;
        this.lastDate = lastDate;
        this.lastDateWithFine = lastDateWithFine;
        this.eligibility = eligibility;
        this.registered = registered;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamFee() {
        return examFee;
    }

    public void setExamFee(String examFee) {
        this.examFee = examFee;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastDateWithFine() {
        return lastDateWithFine;
    }

    public void setLastDateWithFine(String lastDateWithFine) {
        this.lastDateWithFine = lastDateWithFine;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
