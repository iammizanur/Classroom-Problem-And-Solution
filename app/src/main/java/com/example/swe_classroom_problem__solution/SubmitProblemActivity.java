package com.example.swe_classroom_problem__solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubmitProblemActivity extends AppCompatActivity {
    EditText et_varsity_id, et_problem_description;
    Spinner room_no;
    RadioGroup radioGroup;
    RadioButton rdoAC,rdoPC,rdoPROJECTOR,rdoFAN,rdoOTHERS,checkedRadioButton;
    List<Room> roomList;
    List<String> allRoomList;
    Button submit_btn;
    String category;
    String roomname;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_problem);
        et_varsity_id = findViewById(R.id.et_varsity_id);
        room_no = findViewById(R.id.room_no_id);
        et_problem_description = findViewById(R.id.problem_description_id);
        submit_btn = findViewById(R.id.submit_btn_id);
        radioGroup = findViewById(R.id.radioGroup_id);
        roomList = new ArrayList<>();
        allRoomList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        allRoomList.add("Select Room Number");

        databaseReference.child("Room").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String roomName = ds.child("roomName").getValue(String.class);
                    roomList.add(new Room(roomName));
                    allRoomList.add(roomName);
                }
                Log.d("RoomList",String.valueOf(allRoomList.size()) );

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SubmitProblemActivity.this, android.R.layout.simple_spinner_item, allRoomList); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                room_no.setAdapter(spinnerArrayAdapter);

                room_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        roomname=adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(SubmitProblemActivity.this, roomname, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        roomname=adapterView.getItemAtPosition(0).toString();
                    }
                });



                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {

                        checkedRadioButton=radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                        if (checkedRadioButton.isChecked()){
                            category=checkedRadioButton.getText().toString();
                        }



                    }
                });



                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sharedPreferences = getSharedPreferences("MyPrefs",
                                Context.MODE_PRIVATE);
                        String email = sharedPreferences.getString("Email", "");
                        Problem problem=new Problem(et_varsity_id.getText().toString(),roomname,category,et_problem_description.getText().toString(),email,databaseReference.push().getKey());
                        databaseReference.child("Problemlist").child(databaseReference.push().getKey()).setValue(problem).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SubmitProblemActivity.this, "Submit successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SubmitProblemActivity.this, "Unexpected Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
