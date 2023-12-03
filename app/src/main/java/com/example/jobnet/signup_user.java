package com.example.jobnet;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import android.net.Uri;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


public class signup_user extends AppCompatActivity {

    EditText name,email,contact,password,passConf,summary;
    Button signupbtn;
    RadioButton user,compagny;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);
        Intent i=getIntent();

        name=findViewById(R.id.firstName);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        password=findViewById(R.id.password);
        passConf=findViewById(R.id.passConf);
        summary=findViewById(R.id.summary);
        user=findViewById(R.id.user);
        compagny=findViewById(R.id.compagny);
        signupbtn=findViewById(R.id.signUser);



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

    }

    private void saveUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(signup_user.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        uploadData();
    }

    public void uploadData(){
        String full = name.getText().toString();
        String Email = email.getText().toString();
        String Contact=contact.getText().toString();
        String Password=password.getText().toString();
        String Summary=contact.getText().toString();
        String role="";
        if (user.isChecked()) role="user";
        else role="compagny";

        User user = new User(1, full, Email, Contact,Password,role,Summary);

        FirebaseDatabase.getInstance().getReference("User").child(full).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(signup_user.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();

                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signup_user.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}