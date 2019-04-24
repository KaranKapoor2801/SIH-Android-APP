package com.example.workerattendance.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.workerattendance.Config;
import com.example.workerattendance.R;
import com.example.workerattendance.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataRetreived extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    ArrayList<Task>tasksList;
    public static String constructorId, siteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retreived);
        listView = findViewById(R.id.list_view);
        Bundle b = getIntent().getExtras();
        constructorId = b.getString(Config.CONSTRUCTOR);
        siteId = b.getString(Config.SITE_ID);
        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Config.SITE_DATA)
                .child(constructorId)
                .child(Config.SITE_ID)
                .child(Config.TASKS)
        ;

        tasksList = new ArrayList<>();


    }
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot  task: dataSnapshot.getChildren()) {
                    tasksList.add(task.getValue(Task.class));
                }
                taskAdapter myAdapter = new taskAdapter(getApplicationContext(), tasksList);
                listView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
