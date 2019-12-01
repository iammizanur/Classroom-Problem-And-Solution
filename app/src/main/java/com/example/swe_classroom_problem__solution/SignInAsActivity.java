package com.example.swe_classroom_problem__solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SignInAsActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;

    LinearLayout ll_userregistration;
    private Button btn_admin,btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as);
        btn_admin = findViewById(R.id.btn_admin);
        btn_user = findViewById(R.id.btn_user);
        ll_userregistration=findViewById(R.id.user_registrationId);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInAsActivity.this,AdminLoginActivity.class));
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInAsActivity.this, UserLoginActivity.class));
            }
        });

    }

    public void gotoSignUP(View view) {
      startActivity(new Intent(SignInAsActivity.this,UserRegistrationActivity.class));
    }
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
