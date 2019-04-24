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
import android.widget.TextView;
import android.widget.Toast;

import com.example.workerattendance.AttendanceActivity;
import com.example.workerattendance.Config;
import com.example.workerattendance.DataOfCardMainSite;
import com.example.workerattendance.R;
import com.example.workerattendance.SiteActionActivity;
import com.example.workerattendance.TextToPdfActivity;
import com.example.workerattendance.models.DataOfSite;
import com.example.workerattendance.models.DataUpdateFromClientSite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvailableSiteListAdapter extends ArrayAdapter<DataUpdateFromClientSite>{
    Context mContext;
    ArrayList<DataUpdateFromClientSite> mMainSiteData;


    public AvailableSiteListAdapter(@NonNull Context context, @NonNull ArrayList<DataUpdateFromClientSite> objects) {
        super(context, R.layout.design_avaliable_site_list_view, objects);
        mContext=context;
        mMainSiteData=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        CardViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.design_avaliable_site_list_view,parent, false);
            viewHolder = new CardViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (CardViewHolder)view.getTag();
        viewHolder.siteName.setText(mMainSiteData.get(position).getSiteName());
        viewHolder.projectDate.setText(mMainSiteData.get(position).getCurrentDate());

        viewHolder.mButtonViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, SiteActionActivity.class);
                i.putExtra(Config.SITE_ID, mMainSiteData.get(position).getSiteId());
                i.putExtra(Config.CONSTRUCTOR, mMainSiteData.get(position).getEmailOfConstructor());
                mContext.startActivity(i);
            }
        });

        viewHolder.shareReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareReport(mMainSiteData.get(position).getSiteId(), mMainSiteData.get(position).getEmailOfConstructor());
            }
        });
        return view;
    }

    class CardViewHolder{
        TextView siteName;
        TextView projectDate;
        Button mButtonViewDetail, shareReport;
        CircleImageView circleImageView;
        CardViewHolder(View view)
        {
            siteName=view.findViewById(R.id.nameOfSite);
            projectDate=view.findViewById(R.id.projectDate);
            mButtonViewDetail=view.findViewById(R.id.viewList);
            shareReport = view.findViewById(R.id.shareReport);
            circleImageView = view.findViewById(R.id.projectTypeImage);
        }
    }
    private void shareReport(String siteId, String emailOfConstructor) {


        FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Config.SITE_DATA)
                .child(emailOfConstructor)
                .child(siteId)
                .child(Config.UPDATES)
                .child(Config.getDate())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String materialUpdateString = getMaterialUpdates(dataSnapshot.child(Config.MATERIAL_UPDATE));
                        String taskUpdateString = getTaskUpdates(dataSnapshot.child("progress"));
                        generatePDF(materialUpdateString + "\n"+ taskUpdateString);
//                        getQAUpdates(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void generatePDF(String s) {
        Intent i = new Intent(getContext(), TextToPdfActivity.class);
        i.putExtra(Config.PDF_VALUE, s);
        getContext().startActivity(i);
    }

    private String getTaskUpdates(DataSnapshot dataSnapshot) {
        String questions[] = new String[]{
                "Labour worked today.",
                "Mishap happened.",
                "Material shortages occured.",
                "Inspection occurred today.",
                "Task Completed."
        };
        String responses = dataSnapshot.getValue().toString();
        String yesAnswered = "YES:\n";
        String noAnswered = "NO:\n";
        for (int i = 0; i < 5; i++) {
            if (Integer.parseInt("" + responses.charAt(i)) == 1) {
                yesAnswered = yesAnswered.concat(questions[i] + "\n");
            } else {
                noAnswered = noAnswered.concat(questions[i] + "\n");
            }
        }
        return yesAnswered + "\n" + noAnswered;
    }

    private String getMaterialUpdates(DataSnapshot dataSnapshot) {
        String shortage = "Material Shortage:\n";
        String used = "Material Usage:\n";
        Iterable<DataSnapshot> materialUpdate = dataSnapshot.getChildren();
        for(DataSnapshot update: materialUpdate) {
            if(Boolean.parseBoolean(update.child("shortage").getValue().toString()))  {
                shortage = shortage.concat(dataSnapshot.getKey()+"\n");
            }
            if(Boolean.parseBoolean(update.child("used").getValue().toString())) {
                used = used.concat(dataSnapshot.getKey()+"\n");
            }
        }
        return shortage + "\n" + used;
    }
}
