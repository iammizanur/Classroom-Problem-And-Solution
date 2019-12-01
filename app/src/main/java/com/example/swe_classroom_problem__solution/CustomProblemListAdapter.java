package com.example.swe_classroom_problem__solution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CustomProblemListAdapter extends BaseAdapter {

    Context context;
    List<Problem>problemList;

    public CustomProblemListAdapter(Context context, List<Problem> problemList) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.custom_problem_list,viewGroup,false);

        }
        TextView tv_st_id, tv_roomNo, tv_category,tv_description;
        tv_st_id = view.findViewById(R.id.tv_custom_student_id);
        tv_roomNo = view.findViewById(R.id.tv_custom_room_id);
        tv_category = view.findViewById(R.id.problem_category_id);
        tv_description = view.findViewById(R.id.custom_description);
        tv_st_id.setText(problemList.get(i).getId());
        tv_roomNo.setText(problemList.get(i).getRoomNo());
        tv_category.setText(problemList.get(i).getCategory());
        tv_description.setText(problemList.get(i).getDescription());
        return view;
    }
}
