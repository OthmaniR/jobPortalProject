package com.example.jobnet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // firebaseAuth = FirebaseAuth.getInstance();

        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle login button click
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

               // firebaseAuth.signInWithEmailAndPassword(email, password)
                      //  .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                          //  @Override
                          //  public void onComplete(@NonNull Task<AuthResult> task) {
                             //   if (task.isSuccessful()) {
                                    // Sign in success
                               //     FirebaseUser user = firebaseAuth.getCurrentUser();
                                    // Navigate to the main activity or another part of your app

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish(); // Close the login activity
                               // } else {
                                    // If sign in fails, display a message to the user.
                                   // tilEmail.setError("Invalid email or password");
                                  //  tilPassword.setError("Invalid email or password");
                              //  }
                         //   }


                      //  });
            }
        });
    }
}