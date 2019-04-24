package com.example.workerattendance;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowTaskToClient extends AppCompatActivity {

    Button b;
    CalendarView karancal;
    LinearLayout linearLayout;
    TextView[] textViewReferences;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    /**
     * TODO: Remove this hardcoding from intent data transfer
     */
    public String siteId = "h10nitk";
    String constructorId;
    public TextView t1,t2,t3;
    String karans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivit_client_task_show);
        Bundle b = getIntent().getExtras();
        siteId = b.getString(Config.SITE_ID);
        constructorId = b.getString(Config.EMAIL_OF_CONSTRUCTOR);

        linearLayout = findViewById(R.id.My_id);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        karancal=(CalendarView)findViewById(R.id.karanView3);
        t1=(TextView)findViewById(R.id.karanT2);
        karancal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = ""+dayOfMonth+month+year;
                fetchTaskUpdates(date);
            }
        });
    }

    private void fetchTaskUpdates(String date) {
        DatabaseReference dateReference = databaseReference
                .child(Config.SITE_DATA)
                .child(constructorId)
                .child(siteId)
                .child(Config.UPDATES)
                .child(date);

        dateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot taskUpdateSnapshot = dataSnapshot.child(Config.PROGRESS);
                showTaskUpdates(taskUpdateSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showTaskUpdates(DataSnapshot taskSnapshot) {
        for(DataSnapshot task: taskSnapshot.getChildren()) {
            Task currentTask = new Task(task);
            final TextView newTextView = new TextView(this);
            t2=(TextView) findViewById(R.id.karanT3);
            t2.setText("Percentage "+currentTask.percentage);
            t3=(TextView) findViewById(R.id.karanT4);
            t3.setText("Reason "+currentTask.reason);
        }
    }
}
