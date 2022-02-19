package com.example.coe_complaints;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Complaint extends RealmObject {
    @PrimaryKey
    @Required
    private Integer _id;

    private String addressedBy;

    private String addressedOnDate;

    private String issueDetails;

    private String issueName;

    @Required
    private String partition_key;

    public Complaint()
    {

    }

    public Complaint(Integer _id,String raisedBy, String issueName, String issueDetails,  String raisedOnDate, String status) {
        this._id = _id;
        this.issueDetails = issueDetails;
        this.issueName = issueName;
        this.raisedBy = raisedBy;
        this.raisedOnDate = raisedOnDate;
        this.status = status;
    }

    private String raisedBy;

    private String raisedOnDate;

    private String status;

    // Standard getters & setters
    public Integer get_id() { return _id; }
    public void set_id(Integer _id) { this._id = _id; }

    public String getAddressedBy() { return addressedBy; }
    public void setAddressedBy(String addressedBy) { this.addressedBy = addressedBy; }

    public String getAddressedOnDate() { return addressedOnDate; }
    public void setAddressedOnDate(String addressedOnDate) { this.addressedOnDate = addressedOnDate; }

    public String getIssueDetails() { return issueDetails; }
    public void setIssueDetails(String issueDetails) { this.issueDetails = issueDetails; }

    public String getIssueName() { return issueName; }
    public void setIssueName(String issueName) { this.issueName = issueName; }

    public String getPartition_key() { return partition_key; }
    public void setPartition_key(String partition_key) { this.partition_key = partition_key; }

    public String getRaisedBy() { return raisedBy; }
    public void setRaisedBy(String raisedBy) { this.raisedBy = raisedBy; }

    public String getRaisedOnDate() { return raisedOnDate; }
    public void setRaisedOnDate(String raisedOnDate) { this.raisedOnDate = raisedOnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isPending()
    {
        if(status.toLowerCase().equals("pending"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
