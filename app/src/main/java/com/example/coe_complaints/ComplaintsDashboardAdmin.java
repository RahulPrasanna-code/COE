package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class ComplaintsDashboardAdmin extends AppCompatActivity {

    private TextView txtTotalComplaints,txtPendingComplaints,txtAddressedComplaints,txtAddressingRate;
    private MaterialCardView btnPendingComplaints,btnAddressedComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_dashboard_admin);

        btnPendingComplaints = findViewById(R.id.btnPendingComplaints);
        btnAddressedComplaints = findViewById(R.id.btnAddressedComplaints);

        txtTotalComplaints = findViewById(R.id.txtTotalComplaints);
        txtPendingComplaints = findViewById(R.id.txtPendingComplaints);
        txtAddressedComplaints =findViewById(R.id.txtAddressedComplaints);
        txtAddressingRate = findViewById(R.id.txtAddressingRate);

        btnPendingComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PendingComplaintsActivity.class));
            }
        });


    }
}