package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import io.realm.Realm;

public class ExamRegistrationForm extends AppCompatActivity {

    private String feeAmount[] = new String[1];
    private EditText txtName,txtRegisterNo;
    private MaterialCardView cardFeeDetails;
    private AutoCompleteTextView feeAutoCompleteView;
    private ArrayAdapter<String> adapterItems;
    private int examId;
    private Realm backgroundThreadRealm;
    private Exam exam;
    private TextView txtExamFee,txtCenterFee,txtGST,txtTax,txtTotal;
    private boolean isALlFieldsChecked=false;
    private MaterialButton btnPayment,btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_registration_form);

        feeAutoCompleteView = findViewById(R.id.listFeeDetails);
        txtName = findViewById(R.id.txtName);
        txtRegisterNo = findViewById(R.id.txtRegisterNo);
        cardFeeDetails = findViewById(R.id.cardFeeDetails);
        cardFeeDetails.setVisibility(View.GONE);

        txtExamFee = findViewById(R.id.txtExamFee);
        txtCenterFee = findViewById(R.id.txtCenterFee);
        txtGST = findViewById(R.id.txtGST);
        txtTax = findViewById(R.id.txtTax);
        txtTotal = findViewById(R.id.txtTotal);

        btnPayment = findViewById(R.id.btnPayment);
        btnReset = findViewById(R.id.btnReset);


        examId = getIntent().getIntExtra("id",0);

        backgroundThreadRealm = Realm.getDefaultInstance();

        backgroundThreadRealm.executeTransaction(realm->{
            exam = realm.where(Exam.class).equalTo("_id",examId).findFirst();
        });

        feeAmount[0] = "Fee Amount : "+exam.getExamFee();
        adapterItems = new ArrayAdapter<String>(this,R.layout.fee_item,feeAmount);
        feeAutoCompleteView.setAdapter(adapterItems);

        feeAutoCompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String complaint_type = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),complaint_type,Toast.LENGTH_LONG).show();

                txtExamFee.setText(exam.getExamFee());
                txtCenterFee.setText("-");
                txtGST.setText("-");
                txtTax.setText("-");
                txtTotal.setText(exam.getExamFee());


                cardFeeDetails.setVisibility(View.VISIBLE);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeAutoCompleteView.setText("");
                txtName.setText("");
                txtRegisterNo.setText("");
                cardFeeDetails.setVisibility(View.GONE);
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isALlFieldsChecked = checkAllFields();

                if(isALlFieldsChecked)
                {
                    Toast.makeText(getApplicationContext(),"Making Payment",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private boolean checkAllFields() {
        if(txtName.length()==0)
        {
            txtName.setError("required");
            return false;
        }else if(txtRegisterNo.length()==0)
        {
            txtRegisterNo.setError("required");
            return false;
        }else if(feeAutoCompleteView.length()==0)
        {
            feeAutoCompleteView.setError("required");
            return false;
        }else
        {
            return true;
        }
    }
}