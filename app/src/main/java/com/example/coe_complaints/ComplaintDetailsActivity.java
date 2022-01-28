package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ComplaintDetailsActivity extends AppCompatActivity {

    private Complaint complaint;
    private TextView txtIssueName,txtIssueDescription;
    private MaterialButton btnWithdrawComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);

        complaint = (Complaint) getIntent().getSerializableExtra("complaint");

        txtIssueName = findViewById(R.id.txtIssueName);
        txtIssueDescription = findViewById(R.id.txtIssueDescription);
        btnWithdrawComplaint = findViewById(R.id.btnWithDraw);

        txtIssueName.setText(complaint.getIssueName());
        txtIssueDescription.setText(complaint.getIssueDetails());


    }
}