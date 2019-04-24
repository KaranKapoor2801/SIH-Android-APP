package com.example.workerattendance.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.workerattendance.Config;
import com.example.workerattendance.R;
import com.example.workerattendance.TextToPdfActivity;
import com.example.workerattendance.models.DataUpdateFromClientSite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListOfAvaliableClientSiteAdapter extends ArrayAdapter<DataUpdateFromClientSite> {
    private ArrayList<DataUpdateFromClientSite> mDataUpdate;
    private Context mContext;
    public ListOfAvaliableClientSiteAdapter(@NonNull Context context, @NonNull ArrayList<DataUpdateFromClientSite> objects) {
        super(context, R.layout.design_avaliable_site_list_view, objects);
        mDataUpdate =objects;
        mContext=context;
    }
    //For Using Data Holding
    class ViewHolder {
        CircleImageView mImageView;
        TextView mSiteName;
        TextView mDate;
        Button shareReportButton;
        ViewHolder(View view){
            mSiteName=(TextView)view.findViewById(R.id.nameOfSite);
            mDate=(TextView)view.findViewById(R.id.projectDate);
            mImageView=(CircleImageView) view.findViewById(R.id.projectTypeImage);
            shareReportButton = view.findViewById(R.id.shareReport);
        }
    }
    //Called method When Single View Called
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View mViewAttendance=convertView;
        final ListOfAvaliableClientSiteAdapter.ViewHolder holder;
        if(mViewAttendance==null)
        {
            mViewAttendance=LayoutInflater.from(mContext).inflate(R.layout.design_avaliable_site_list_view,parent,false);
            holder=new ListOfAvaliableClientSiteAdapter.ViewHolder(mViewAttendance);
            mViewAttendance.setTag(holder);
        }
        else
            holder=(ListOfAvaliableClientSiteAdapter.ViewHolder)mViewAttendance.getTag();
        holder.mSiteName.setText(mDataUpdate.get(position).getSiteName());
        holder.mDate.setText(mDataUpdate.get(position).getCurrentDate());
        if(mDataUpdate.get(position).getProjectType()== Config.CIVIL)
        {
            holder.mImageView.setImageResource(R.drawable.app_logo);
        }
        else
        {
            holder.mImageView.setImageResource(R.drawable.backand_butten);
        }

        return mViewAttendance;
    }
}
