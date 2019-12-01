package com.example.swe_classroom_problem__solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLoginActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText etEmail, etPassword;
    Button signIn;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_up);
        progressBar = findViewById(R.id.user_progressBar);
        etEmail = findViewById(R.id.et_emailBtnId);
        etPassword = findViewById(R.id.et_passwordBtnId);
        signIn = findViewById(R.id.signInBtnId);
        firebaseAuth = FirebaseAuth.getInstance();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                final String email, password;
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(UserLoginActivity.this, "Fill up all the required fields", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult.getUser().isEmailVerified()) {
                                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                progressBar.setVisibility(View.GONE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("Email", email
                                );
                                editor.commit();
                                Toast.makeText(UserLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UserLoginActivity.this, UserDashboardActivity.class));
                            }
                            else {
                                Toast.makeText(UserLoginActivity.this, "Email is not verified yet", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(UserLoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
        });
    }

}
