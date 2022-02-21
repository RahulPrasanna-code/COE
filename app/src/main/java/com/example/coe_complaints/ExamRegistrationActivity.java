package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class ExamRegistrationActivity extends AppCompatActivity {

    private MaterialButton btnRegister,btnBack;
    private TextView txtCourseName,txtExamDate,txtEligibility,txtLastDate,txtLastDateWithFine;
    private int examId;
    private Realm backgroundThreadRealm;
    private Exam exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_registration);


        btnBack = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);
        txtCourseName = findViewById(R.id.txtCourseName);
        txtExamDate = findViewById(R.id.txtExamDate);
        txtEligibility = findViewById(R.id.txtEligibility);
        txtLastDate = findViewById(R.id.txtLastDate);
        txtLastDateWithFine = findViewById(R.id.txtLastDateWithFine);

        examId = getIntent().getIntExtra("id",0);

        backgroundThreadRealm = Realm.getDefaultInstance();

        backgroundThreadRealm.executeTransaction(realm->{
            exam = realm.where(Exam.class).equalTo("_id",examId).findFirst();
        });

        txtCourseName.setText(exam.getExamName());
        txtExamDate.setText(exam.getExamDate());
        txtEligibility.setText(exam.getEligibility());
        txtLastDate.setText(exam.getLastDate());
        txtLastDateWithFine.setText(exam.getLastDateWithFine());

        if(exam.isRegistered())
        {
            btnRegister.setText("Hall Ticket");
            btnBack.setText("Withdraw");
        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBackFunction();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegisterFunction();
            }
        });
    }

    private void btnBackFunction() {
        if(exam.isRegistered())
        {
            Toast.makeText(getApplicationContext(),"withdraw exam",Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
        return;
    }

    private void  btnRegisterFunction(){
        if(exam.isRegistered())
        {
            Toast.makeText(getApplicationContext(),"Viewing Hall Ticket ",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(),ExamRegistrationForm.class);
        intent.putExtra("id",examId);
        startActivity(intent);
        return;

    }
}
