package com.example.workerattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.workerattendance.Adapter.DataRetreived;

public class SiteActionActivity extends AppCompatActivity {
    String siteId;
    String constructorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_action);
        siteId = getIntent().getExtras().getString(Config.SITE_ID);
        constructorId = getIntent().getExtras().getString(Config.CONSTRUCTOR);
    }

    public void attendanceLaunch(View view) {
        Intent i = new Intent(this, AttendanceActivity.class);
        i.putExtra(Config.SITE_ID, siteId);
        startActivity(i);
    }

    public void update(View view) {
        Intent i = new Intent(this, UpdateTaskQuestionare.class);
        i = addExtras(i);
        startActivity(i);
    }

    private Intent addExtras(Intent i) {
        i.putExtra(Config.SITE_ID, siteId);
        i.putExtra(Config.CONSTRUCTOR, constructorId);
        return i;
    }

    public void material(View view) {
        Intent i = new Intent(this, AddMaterialActivity.class);
        i = addExtras(i);
        startActivity(i);

    }

    public void tasks(View view) {
        Intent i = new Intent(this, DataRetreived.class);
        i = addExtras(i);
        startActivity(i);
    }

    public void emergency(View view) {

    }
}
