package com.example.coe_complaints;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.filament.Material;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;

public class CreateExamActivity extends AppCompatActivity {

    private EditText txtExamName,txtExamFee, txtEligibility, txtExamDate, txtLastDate, txtLastDateWithFine;
    private MaterialButton btnCreateExam;
    private boolean isALlFieldsChecked=false;
    private String examName,examFee,eligibility,examDate,lastDate,lastDateWithFine;
    private Realm backgroundThreadRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        txtExamName = findViewById(R.id.txtExamName);
        txtExamFee = findViewById(R.id.txtFee);
        txtEligibility = findViewById(R.id.txtEligibility);
        txtExamDate = findViewById(R.id.txtExamDate);
        txtLastDate =  findViewById(R.id.txtLastDate);
        txtLastDateWithFine = findViewById(R.id.txtLastDateWithFine);
        btnCreateExam = findViewById(R.id.btnCreateExam);

        backgroundThreadRealm = Realm.getDefaultInstance();

        btnCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isALlFieldsChecked = checkAllFields();

                if(isALlFieldsChecked)
                {
                    backgroundThreadRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Number num = realm.where(Exam.class).max("_id");
                            int nextID;
                            if(num == null) {
                                nextID = 1;
                            } else {
                                nextID = num.intValue() + 1;
                            }

                            Exam exam = realm.createObject(Exam.class,nextID);

                            exam.setFee(txtExamFee.getText().toString());
                            exam.setExamName(txtExamName.getText().toString());
                            exam.setExamDate(txtExamDate.getText().toString());
                            exam.setEligibility(txtEligibility.getText().toString());
                            exam.setLastDate(txtLastDate.getText().toString());
                            exam.setLastDateWithFine(txtLastDateWithFine.getText().toString());

                            realm.insert(exam);
                        }
                    });

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }

            }
        });


    }

    private boolean checkAllFields() {

        examName = txtExamName.getText().toString();
        examFee = txtExamFee.getText().toString();
        examDate = txtExamDate.getText().toString();
        eligibility = txtEligibility.getText().toString();
        lastDate = txtLastDate.getText().toString();
        lastDateWithFine = txtLastDateWithFine.getText().toString();




        if(examName.length()==0)
        {
            txtExamName.setError("required");
            return false;
        }
        else if(examFee.length()==0)
        {
            txtExamFee.setError("required");
            return false;
        }
        else if(eligibility.length()==0)
        {
            txtEligibility.setError("required");
            return false;
        }
        else if(examDate.length()==0)
        {
            txtExamDate.setError("required");
            return false;
        }
        else if(lastDate.length()==0)
        {
            txtLastDate.setError("required");
            return false;
        }
        else if(lastDateWithFine.length()==0)
        {
            txtLastDateWithFine.setError("required");
            return false;
        }
        else
        {
            return true;
        }


    }

}