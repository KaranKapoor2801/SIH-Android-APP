package com.example.workerattendance.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.workerattendance.DataOfLabor;
import com.example.workerattendance.R;
import com.example.workerattendance.SQLiteHelper;

import java.util.ArrayList;

public class AttendanceAdapter extends ArrayAdapter<DataOfLabor> {
    private ArrayList<DataOfLabor> dataOfLabors;
    private SQLiteHelper helper;
    private Context mContext;
//Initialize Data With Constructor
    public AttendanceAdapter(@NonNull Context context, @NonNull ArrayList<DataOfLabor> objects,SQLiteHelper helper) {
        super(context, R.layout.design_attendance_list, objects);
        dataOfLabors=objects;
        mContext=context;
        this.helper=helper;
    }
    //For Using Data Holding
    class ViewHolder {
        TextView mNameOfLaborTv;
        Button mButtonAttendBt;
        Button mButtonNotAttendBT;
        TextView mSalaryTV;
        TextView mAttendanceTV;
        TextView mCategory;
        ViewHolder(View view){
            mNameOfLaborTv=(TextView)view.findViewById(R.id.nameAttendanceList);
            mSalaryTV=(TextView)view.findViewById(R.id.salaryAttendanceList);
            mAttendanceTV=(TextView)view.findViewById(R.id.showAttendanceList);
            mButtonAttendBt=(Button) view.findViewById(R.id.attendButton);
            mCategory=(TextView)view.findViewById(R.id.catagoryAttendanceList);
            mButtonNotAttendBT=(Button) view.findViewById(R.id.notAttendButton);
        }
    }
    //Called method When Single View Called
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View mViewAttendance=convertView;
        final AttendanceAdapter.ViewHolder holder;
        if(mViewAttendance==null)
        {
            mViewAttendance=LayoutInflater.from(mContext).inflate(R.layout.design_attendance_list,parent,false);
            holder=new AttendanceAdapter.ViewHolder(mViewAttendance);
            mViewAttendance.setTag(holder);
        }
        else
            holder=(AttendanceAdapter.ViewHolder)mViewAttendance.getTag();
        holder.mNameOfLaborTv.setText(dataOfLabors.get(position).getNameOfLabor());
        holder.mCategory.setText(dataOfLabors.get(position).getCategory());
        holder.mSalaryTV.setText(String.valueOf(dataOfLabors.get(position).getPayPerDay()*dataOfLabors.get(position).getAttendanceOfLabor()));
        holder.mAttendanceTV.setText(dataOfLabors.get(position).getAttendanceOfLabor()+"/"+dataOfLabors.get(position).getTotalAttendance());
        holder.mButtonAttendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mAttendanceTV.setText((dataOfLabors.get(position).getAttendanceOfLabor()+1)+"/"+(dataOfLabors.get(position).getTotalAttendance()+1));
                dataOfLabors.get(position).setTotalAttendance(dataOfLabors.get(position).getTotalAttendance()+1);
                dataOfLabors.get(position).setAttendanceOfLabor(dataOfLabors.get(position).getAttendanceOfLabor()+1);
                holder.mSalaryTV.setText(String.valueOf(dataOfLabors.get(position).getPayPerDay()*dataOfLabors.get(position).getAttendanceOfLabor()));
                helper.updateData(dataOfLabors.get(position));
            }
        });
        holder.mButtonNotAttendBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mAttendanceTV.setText(dataOfLabors.get(position).getAttendanceOfLabor()+"/"+(dataOfLabors.get(position).getTotalAttendance()+1));
                dataOfLabors.get(position).setTotalAttendance(dataOfLabors.get(position).getTotalAttendance()+1);
                helper.updateData(dataOfLabors.get(position));
            }
        });
        return mViewAttendance;
    }

}
