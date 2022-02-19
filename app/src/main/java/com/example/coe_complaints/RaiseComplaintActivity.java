package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.coe_complaints.COEApplication.app;


import org.bson.Document;

import io.realm.Realm;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.sync.SyncConfiguration;


public class RaiseComplaintActivity extends AppCompatActivity {


    private String[] complaint_types = {"complaint type 1","complaint type 2","complaint type 3","complaint type 4","complaint type 5"};

    private AutoCompleteTextView autoCompleteTextView;

    private ArrayAdapter<String> adapterItems;

    private String user_name,complaint_name,complaint_type,complaint_description;

    private EditText txtUserName, txtComplaintName, txtComplaintDescription;

    private Button btnRaiseComplaint;

    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_complaint);

        user = app.currentUser();
        Realm backgroundThreadRealm = Realm.getDefaultInstance();


        autoCompleteTextView = findViewById(R.id.listComplaintType);
        txtUserName = findViewById(R.id.txtName);
        txtComplaintName = findViewById(R.id.txtComplaintName);
        txtComplaintDescription = findViewById(R.id.txtComplaintDescription);

        btnRaiseComplaint = findViewById(R.id.btnRaiseComplaint);

        adapterItems = new ArrayAdapter<String>(this,R.layout.complaint_type_item,complaint_types);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                complaint_type = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),complaint_type,Toast.LENGTH_LONG).show();
            }
        });

        btnRaiseComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = txtUserName.getText().toString();
                complaint_name = txtComplaintName.getText().toString();
                complaint_description = txtComplaintDescription.getText().toString();

                if(user_name.length()==0 || complaint_name.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"username and complaint name are necessary",Toast.LENGTH_LONG).show();
                }
                else
                {
                    backgroundThreadRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Number num = realm.where(Complaint.class).max("_id");
                            int nextID;
                            if(num == null) {
                                nextID = 1;
                            } else {
                                nextID = num.intValue() + 1;
                            }

                            Complaint complaint = realm.createObject(Complaint.class,nextID);

                            complaint.setRaisedBy(user_name);
                            complaint.setIssueName(complaint_name);
                            complaint.setIssueDetails(complaint_description);
                            complaint.setRaisedOnDate("21/01/22");
                            complaint.setStatus("pending");

                            realm.insert(complaint);
                        }
                    });



                        startActivity(new Intent(RaiseComplaintActivity.this,ComplaintsActivity.class));

                }

            }
        });


    }
}