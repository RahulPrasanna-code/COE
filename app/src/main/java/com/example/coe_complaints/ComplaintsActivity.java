package com.example.coe_complaints;

import static com.example.coe_complaints.MainActivity.app;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;


import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

public class ComplaintsActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private RecyclerView complaintsRecView;
    private ImageView btnBack;

    private User user;
    private MongoDatabase database;
    private MongoCollection<Complaint> collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        Realm backgroundThreadRealm = Realm.getDefaultInstance();

        complaintsRecView = findViewById(R.id.recViewComplaints);
        btnBack =  findViewById(R.id.btnPrevious);
        btnAdd = findViewById(R.id.btnRaiseTicket);

        RealmResults<Complaint> complaints = backgroundThreadRealm.where(Complaint.class).findAllAsync();


        ComplaintsRecAdapter adapter = new ComplaintsRecAdapter(this);

        complaints.addChangeListener(new RealmChangeListener<RealmResults<Complaint>>() {
            @Override
            public void onChange(RealmResults<Complaint> complaints) {
                adapter.setComplaints(complaints);
            }
        });

        complaintsRecView.setAdapter(adapter);
        complaintsRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintsActivity.this,MainActivity.class));
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintsActivity.this,RaiseComplaintActivity.class));
            }
        });
    }
}