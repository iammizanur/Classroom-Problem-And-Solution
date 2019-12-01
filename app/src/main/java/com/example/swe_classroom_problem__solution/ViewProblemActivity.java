package com.example.swe_classroom_problem__solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProblemActivity extends AppCompatActivity {

    ListView admin_problem_listView;
    DatabaseReference databaseReference;
    List<Problem> problemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problem);
        admin_problem_listView = findViewById(R.id.admin_view_problem);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        problemList=new ArrayList<>();

        databaseReference.child("Problemlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String catgory=ds.child("category").getValue(String.class);
                    String description=ds.child("description").getValue(String.class);
                    String email=ds.child("email").getValue(String.class);
                    String id=ds.child("id").getValue(String.class);
                    String roomNo=ds.child("roomNo").getValue(String.class);
                    String key=ds.child("key").getValue(String.class);
                    problemList.add(new Problem(id,roomNo,catgory,description,email,key));
                }

                CustomAdminProblemListAdapter customProblemListAdapter=new CustomAdminProblemListAdapter(ViewProblemActivity.this,problemList);
                admin_problem_listView.setAdapter(customProblemListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewProblemActivity.this,AdminDashboardActivity.class));
        super.onBackPressed();
    }
}
