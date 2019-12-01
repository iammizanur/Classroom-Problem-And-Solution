package com.example.swe_classroom_problem__solution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboardActivity extends AppCompatActivity {

    CardView card_AddRoom, card_ViewProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        card_AddRoom = findViewById(R.id.card_AddRoomNo);
        card_ViewProblem = findViewById(R.id.card_ViewProblemList);
        card_ViewProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this,ViewProblemActivity.class));
            }
        });
        card_AddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AddRoomActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AdminDashboardActivity.this, UserLoginActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
