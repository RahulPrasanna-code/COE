package com.example.coe_complaints;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Complaint extends RealmObject implements Serializable {

    @PrimaryKey()
    private long id;
    private String raisedBy;
    private String issueName;
    private String raisedOnDate;
    private String status;
    private String issueDetails;
    private String addressedBy;
    private String addressedOnDate;

    public Complaint() {
    }

    public Complaint(long id,String raisedBy, String issueName, String issueDetails, String raisedOnDate, String status) {
        this.id+=id;
        this.raisedBy = raisedBy;
        this.issueName = issueName;
        this.raisedOnDate = raisedOnDate;
        this.status = status;
        this.issueDetails = issueDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressedBy() {
        return addressedBy;
    }

    public void setAddressedBy(String addressedBy) {
        this.addressedBy = addressedBy;
    }

    public String getAddressedOnDate() {
        return addressedOnDate;
    }

    public void setAddressedOnDate(String addressedOnDate) {
        this.addressedOnDate = addressedOnDate;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getRaisedOnDate() {
        return raisedOnDate;
    }

    public void setRaisedOnDate(String raisedOnDate) {
        this.raisedOnDate = raisedOnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "issueName='" + issueName + '\'' +
                ", raisedOnDate='" + raisedOnDate + '\'' +
                ", status='" + status + '\'' +
                ", issueDetails='" + issueDetails + '\'' +
                '}';
    }

    public boolean isPending()
    {
        if(status.toLowerCase().equals("pending"))
            return true;
        else
            return false;
    }

    public boolean isAddressed()
    {
        if (status.toLowerCase().equals("done"))
            return true;
        else
            return false;
    }

    public void address()
    {

    }
}
