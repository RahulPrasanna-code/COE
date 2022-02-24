package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    private String examsList[] = {"Exam1","Exam2","Exam3","Exam4","Exam5"};
    private ArrayAdapter<String> adapter;

    private AutoCompleteTextView listExams;
    private Button btnViewResult;
    private ImageView btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        listExams = findViewById(R.id.listExams);
        btnViewResult = findViewById(R.id.btnViewResult);
        btnPrev = findViewById(R.id.btnPrevious);

        adapter = new ArrayAdapter<String>(this,R.layout.complaint_type_item,examsList);

        listExams.setAdapter(adapter);

        listExams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exam_name = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), exam_name,Toast.LENGTH_LONG).show();
            }
        });


        btnViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewResultActivity.class));
            }
        });


    }
}