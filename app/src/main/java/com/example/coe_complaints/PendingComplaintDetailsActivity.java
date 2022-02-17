package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class PendingComplaintDetailsActivity extends AppCompatActivity {

    private Complaint complaint;
    private TextView txtIssueName,txtIssueDescription;
    private MaterialButton btnAddressComplaint,btnMoveToAddressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_complaint_details);

        txtIssueName = findViewById(R.id.txtIssueName);
        txtIssueDescription = findViewById(R.id.txtIssueDescription);

        btnAddressComplaint = findViewById(R.id.btnAddressComplaint);
        btnMoveToAddressed = findViewById(R.id.btnMoveToAddressed);

        btnAddressComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Address Complaint",Toast.LENGTH_SHORT).show();
            }
        });

        btnMoveToAddressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Move to Addressed Complaint",Toast.LENGTH_SHORT).show();
            }
        });

    }
}