package com.example.swe_classroom_problem__solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserViewProblemActivity extends AppCompatActivity {

    ListView user_problem_listView;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    List<Problem>problemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_problem);
        user_problem_listView = findViewById(R.id.user_problem_list_view_id);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        problemList=new ArrayList<>();
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("Email", "");
        databaseReference.child("Problemlist").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
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

                CustomProblemListAdapter customProblemListAdapter=new CustomProblemListAdapter(UserViewProblemActivity.this,problemList);
                user_problem_listView.setAdapter(customProblemListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
