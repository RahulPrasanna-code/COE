package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class PendingComplaintsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView complaintsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_complaints);

        ArrayList<Complaint> complaints = new ArrayList<>();

        complaints.add(new Complaint(1,"Rahul","Complaint 1","This is Complaint 1 description","11/12/22","pending"));
        complaints.add(new Complaint(2,"Thirumalai","Complaint 2","This is Complaint 2 description","12/12/22","done"));
        complaints.add(new Complaint(3,"Rahul","Complaint 3","This is Complaint 3 description","13/12/22","done"));
        complaints.add(new Complaint(4,"Tharun","Complaint 4","This is Complaint 4 description","15/12/22","pending"));
        complaints.add(new Complaint(5,"Arun","Complaint 5","This is Complaint 5 description","17/12/22","pending"));
        complaints.add(new Complaint(6,"Yashwanth","Complaint 6","This is Complaint 6 description","19/12/22","pending"));

        complaintsRecView = findViewById(R.id.recViewPendingComplaints);
        btnBack =  findViewById(R.id.btnPrevious);

        PendingComplaintsRecAdapter pendingComplaintsRecAdapter = new PendingComplaintsRecAdapter(this);
        pendingComplaintsRecAdapter.setComplaints(complaints);

        complaintsRecView.setAdapter(pendingComplaintsRecAdapter);
        complaintsRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PendingComplaintsActivity.this,ComplaintsDashboardAdmin.class));
                finish();
            }
        });

    }
}