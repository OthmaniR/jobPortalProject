package com.example.jobnet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class PostJobActivity extends AppCompatActivity {
    TextView etDate;
    TextView tvDate;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        etDate = findViewById(R.id.job_startdate);
        tvDate = findViewById(R.id.job_tvstartdate);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(PostJobActivity.this, (view, year1, month1, dayOfMonth) -> {
                month1 = month1 + 1;
                String date = dayOfMonth + "/" + month1 + "/" + year1;
                etDate.setText(date);
            }, year, month, dayofmonth);
            datePickerDialog.show();
        });
        setListener = (view, year12, month12, dayOfMonth) -> {
            month12 = month12 + 1;
            String date = dayOfMonth + "/" + month12 + "/" + year12;
            etDate.setText(date);
        };


    }
}