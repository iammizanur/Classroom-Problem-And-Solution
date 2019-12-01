package com.example.swe_classroom_problem__solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoomActivity extends AppCompatActivity {

    private EditText roomEditText;
    private Button addRoomBtn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        roomEditText = findViewById(R.id.addRoomEditTextId);
        addRoomBtn= findViewById(R.id.addRoomBtnId);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        addRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseReference.push().getKey();
                Room room = new Room(roomEditText.getText().toString());
                databaseReference.child("Room").child(id).setValue(room).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddRoomActivity.this, "Successful Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddRoomActivity.this, "Unexpected Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
