package com.example.jobnet;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InsertJobPostActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    TextView etDate;
    EditText etJobTitle, etJobDate ,etJobDescription, etJobLocation, etCategory, etExperience, etSkills;
    Button saveJob;
    Long maxid;
    DatabaseReference database;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);
        etDate = findViewById(R.id.job_startdate);
        etJobTitle = findViewById(R.id.job_title);
        etJobDate = findViewById(R.id.job_startdate);
        etJobDescription = findViewById(R.id.job_Description);
        etJobLocation = findViewById(R.id.job_location);
        etCategory = findViewById(R.id.job_Category);
        etExperience = findViewById(R.id.job_Experience);
        etSkills = findViewById(R.id.job_skillsrequired);

        saveJob = findViewById(R.id.btn_job_post);
        database= FirebaseDatabase.getInstance().getReference("Job");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid=(snapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(InsertJobPostActivity.this, (view, year1, month1, dayOfMonth) -> {
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

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();


                        }
                    }
                }

        );

        saveJob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveData();
            }
        });


    }

    public void  saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(InsertJobPostActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        uploadData();

    }

    public void uploadData(){

        String jobTitle = etJobTitle.getText().toString();
        String jobDate = etJobDate.getText().toString();
        String jobDescription = etJobDescription.getText().toString();
        String jobLocation = etJobLocation.getText().toString();
        String category = etCategory.getText().toString();
        String experience = etExperience.getText().toString();
        String skills = etSkills.getText().toString();



        Job job = new Job(maxid,jobTitle, jobDescription, jobLocation, category, jobDate, experience, skills);
        job.setIdJob(maxid+1);
        FirebaseDatabase.getInstance().getReference("Job").child(job.getIdJob().toString()).setValue(job).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(InsertJobPostActivity.this, "Job Posted Successfully", Toast.LENGTH_SHORT).show();
                etJobTitle.setText("");
                etJobDate.setText("");
                etJobDescription.setText("");
                etJobLocation.setText("");
                etCategory.setText("");
                etExperience.setText("");
                etSkills.setText("");
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InsertJobPostActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}