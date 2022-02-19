package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmResults;

public class ComplaintDetailsActivity extends AppCompatActivity {

    private long complaintId;
    private Complaint complaint;
    private TextView txtIssueName,txtIssueDescription;
    private MaterialButton btnWithdrawComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);

        complaintId = getIntent().getIntExtra("complaint_id",4);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realmTransaction -> {
            complaint = realmTransaction.where(Complaint.class).equalTo("_id",complaintId).findFirst();
        });
        txtIssueName = findViewById(R.id.txtIssueName);
        txtIssueDescription = findViewById(R.id.txtIssueDescription);
        btnWithdrawComplaint = findViewById(R.id.btnWithDraw);

        txtIssueName.setText(complaint.getIssueName());
        txtIssueDescription.setText(complaint.getIssueDetails());


    }
}