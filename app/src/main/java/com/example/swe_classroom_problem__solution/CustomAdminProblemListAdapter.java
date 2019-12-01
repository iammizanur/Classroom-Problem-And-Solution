package com.example.swe_classroom_problem__solution;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CustomAdminProblemListAdapter extends BaseAdapter {


    Context context;
    List<Problem> problemList;
    Button deleteButton;

    public CustomAdminProblemListAdapter(Context context, List<Problem> problemList) {
        this.context = context;
        this.problemList = problemList;
    }

    @Override
    public int getCount() {
        return problemList.size();
    }

    @Override
    public Object getItem(int i) {
        return problemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.custom_admin_problem_list,viewGroup,false);

        }
        TextView tv_st_id, tv_roomNo, tv_category,tv_description;
        tv_st_id = view.findViewById(R.id.tv_admin_custom_student_id);
        tv_roomNo = view.findViewById(R.id.tv_admin_custom_room_id);
        tv_category = view.findViewById(R.id.admin_problem_category_id);
        tv_description = view.findViewById(R.id.admin_custom_description);
        tv_st_id.setText(problemList.get(i).getId());
        tv_roomNo.setText(problemList.get(i).getRoomNo());
        tv_category.setText(problemList.get(i).getCategory());
        tv_description.setText(problemList.get(i).getDescription());
        deleteButton = view.findViewById(R.id.admin_delete_id);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Problemlist").orderByChild("key").equalTo(problemList.get(i).getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                            Toast.makeText(context, "Problem deleted", Toast.LENGTH_SHORT).show();
                            problemList.remove(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                context.startActivity(new Intent(context,ViewProblemActivity.class));
            }
        });
        return view;
    }
}
