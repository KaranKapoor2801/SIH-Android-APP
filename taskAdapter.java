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
import com.itextpdf.text.pdf.parser.Line;


import java.util.ArrayList;

public class taskAdapter extends ArrayAdapter<Task>{
    Context mContext;
    ArrayList<Task> mMainSiteData;


    public taskAdapter(@NonNull Context context, @NonNull ArrayList<Task> objects) {
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
        viewHolder.duration.setText(mMainSiteData.get(position).getDuration());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, SiteActionActivity.class);
                i.putExtra(Config.SITE_ID, DataRetreived.siteId);
                i.putExtra(Config.CONSTRUCTOR, DataRetreived.constructorId);
                i.putExtra(Config.TASK_ID, position);
                mContext.startActivity(i);
            }
        });

        return view;
    }

    class CardViewHolder{
        TextView taskName;
        TextView duration;
        LinearLayout linearLayout;
        CardViewHolder(View view)
        {
            taskName=view.findViewById(R.id.taskName);
            duration=view.findViewById(R.id.expectedDuration);
            linearLayout = view.findViewById(R.id.taskListElement);
        }
    }
}
