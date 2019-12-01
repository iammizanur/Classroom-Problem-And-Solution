package com.example.swe_classroom_problem__solution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserDashboardActivity extends AppCompatActivity {
    CardView add_problem;
    CardView view_problem_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        add_problem = findViewById(R.id.card_view_addProblem);
        view_problem_list = findViewById(R.id.card_ViewProblemList);
        add_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboardActivity.this, SubmitProblemActivity.class));
            }
        });
        view_problem_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboardActivity.this, UserViewProblemActivity.class));
            }
        });
    }
}
