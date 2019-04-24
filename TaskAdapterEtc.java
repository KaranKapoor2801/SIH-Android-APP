package com.example.workerattendance.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workerattendance.AddTaskActivity;
import com.example.workerattendance.Config;
import com.example.workerattendance.R;
import com.example.workerattendance.SiteActionActivity;
import com.example.workerattendance.Task;
import com.example.workerattendance.models.DataOfSite;


import java.util.ArrayList;

public class TaskAdapterEtc extends ArrayAdapter<Task>{
    Context mContext;
    ArrayList<Task> mMainSiteData;


    public TaskAdapterEtc(@NonNull Context context, @NonNull ArrayList<Task> objects) {
        super(context, R.layout.task_list_element, objects);
        mContext=context;
        mMainSiteData=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        final CardViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.task_list_element,parent, false);
            viewHolder = new CardViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) view.getTag();
        }
        viewHolder.taskName.setText(mMainSiteData.get(position).getTaskName());
        viewHolder.taskDuration.setText(""+mMainSiteData.get(position).getDuration());
        viewHolder.taskIndex.setText(""+(position+1));
        setBackgroundColor(viewHolder, position);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskActivity.taskList.get(position).setIsSelected(!AddTaskActivity.taskList.get(position).getIsSelected());
                setBackgroundColor(viewHolder, position);
            }
        });
        viewHolder.editTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo change name or duration
            }
        });
        return view;
    }

    private void setBackgroundColor(CardViewHolder viewHolder, int position) {
        if(AddTaskActivity.taskList.get(position).getIsSelected()) {
            viewHolder.linearLayout.setBackgroundColor(getContext().getResources().getColor(R.color.white_background));
        } else {
            viewHolder.linearLayout.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    class CardViewHolder{
        TextView taskName;
        TextView taskDuration;
        TextView taskIndex;
        Button editTask;
        LinearLayout linearLayout;
        CardViewHolder(View view)
        {
            taskName=view.findViewById(R.id.taskName);
            taskDuration=view.findViewById(R.id.expectedDuration);
            taskIndex=view.findViewById(R.id.taskIndex);
            editTask = view.findViewById(R.id.editTask);
            linearLayout = view.findViewById(R.id.taskListElement);
        }
    }
}
