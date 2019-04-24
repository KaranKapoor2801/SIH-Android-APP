package com.example.workerattendance;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.workerattendance.Adapter.AvailableSiteListAdapter;
import com.example.workerattendance.models.DataOfSite;
import com.example.workerattendance.models.DataUpdateFromClientSite;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MainSiteActivity extends AppCompatActivity {

    ListView mListDataOfSiteLv;
    ArrayList<DataUpdateFromClientSite> dataOfSites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_site);
        mListDataOfSiteLv=(ListView)findViewById(R.id.sites_list);
        dataOfSites=new ArrayList<>();
        getDataFromFireBase();
    }
    public void getDataFromFireBase()
    {
        String emailOfConstructor= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        emailOfConstructor = emailOfConstructor.replace('.','_');
        FirebaseDatabase.getInstance().getReference().
                child(Config.SITE_DATA).child(emailOfConstructor).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot snapshot: it) {
                    dataOfSites.add(snapshot.getValue(DataUpdateFromClientSite.class));
                }
                addAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addAdapter()
    {
        AvailableSiteListAdapter adapter=new AvailableSiteListAdapter(getApplicationContext(), dataOfSites);
        mListDataOfSiteLv.setAdapter(adapter);
    }
}
