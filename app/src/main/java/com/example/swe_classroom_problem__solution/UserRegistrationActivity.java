package com.example.swe_classroom_problem__solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistrationActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPass, etPhoneNo;
    private Button registrationBtn;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        etEmail = findViewById(R.id.etEmailEditTextId);
        etPassword = findViewById(R.id.etPasswordEditTextId);
        etConfirmPass = findViewById(R.id.etConfirmPasswordEditTextId);
        etPhoneNo = findViewById(R.id.etPhoneEditTextId);
        registrationBtn = findViewById(R.id.registrationBtnId);
        etName =findViewById(R.id.etNameEditTextId);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name,Email,password,confirmPassword,phoneNo;
                Name = etName.getText().toString();
                Email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                confirmPassword = etConfirmPass.getText().toString();
                phoneNo = etPhoneNo.getText().toString();

                if(Name.isEmpty() && Email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() && phoneNo.isEmpty()){
                    Toast.makeText(UserRegistrationActivity.this, "Require all the field", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(confirmPassword)){
                        if (password.length()<6){
                            Toast.makeText(UserRegistrationActivity.this, "Password length must be at least 6 digit", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (Email.contains("@diu.edu.bd")) {
                                firebaseAuth.createUserWithEmailAndPassword(Email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        Toast.makeText(UserRegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                        final FirebaseUser user = authResult.getUser();

                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(UserRegistrationActivity.this, "A verification link has been sent to your account", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UserRegistrationActivity.this, "Email send failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        databaseReference.child("User").child(databaseReference.push().getKey()).setValue(new User(Name, Email, phoneNo)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(UserRegistrationActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UserRegistrationActivity.this, "User addition fail", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        startActivity(new Intent(UserRegistrationActivity.this, UserLoginActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(UserRegistrationActivity.this, "Unexpected Error!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else {
                                Toast.makeText(UserRegistrationActivity.this, "You must use the versity email", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    else {
                        Toast.makeText(UserRegistrationActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
