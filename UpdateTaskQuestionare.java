package com.example.workerattendance;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UpdateTaskQuestionare extends AppCompatActivity {

    Switch sw1,sw2,sw3,sw4,sw5;
    Button updatebtn;
    String str;
    String constructorId;
    String siteId;
    int ar[];
    private FirebaseDatabase dref;
    private DatabaseReference refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref=FirebaseDatabase.getInstance();
        ar = new int[5];
        constructorId = getIntent().getExtras().getString(Config.CONSTRUCTOR);
        siteId= getIntent().getExtras().getString(Config.SITE_ID);
        for(int i=0;i<5;i++) {
            ar[i] = 0;
        }
        refer=dref.getReference();
        updatebtn=(Button)findViewById(R.id.upadtebtn);
        sw1=(Switch)findViewById(R.id.mishappening);
        sw2=(Switch)findViewById(R.id.workedtoday);
        sw3=(Switch)findViewById(R.id.materialshortage);
        sw4=(Switch)findViewById(R.id.inspection);
        sw5=(Switch)findViewById(R.id.tskecompletion);
        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on=((Switch)view).isChecked();
                if(on)
                    ar[0] = 1;
                else
                    ar[0]=0;
            }
        });
        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on=((Switch)view).isChecked();
                if(on)
                    ar[1] = 1;
                else
                    ar[1]=0;
            }
        });
        sw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on=((Switch)view).isChecked();
                if(on)
                    ar[2] = 1;
                else
                    ar[2]=0;
            }
        });
        sw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on=((Switch)view).isChecked();
                if(on)
                    ar[3] = 1;
                else
                    ar[3]=0;
            }
        });
        sw5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on=((Switch)view).isChecked();
                if(on)
                    ar[4] = 1;
                else
                    ar[4]=0;
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = "";
                for(int i=0;i<5;i++) {
                    str+=""+ar[i];
                }
                refer
                        .child(Config.SITE_DATA)
                        .child(constructorId)
                        .child(siteId)
                        .child(Config.UPDATES)
                        .child(Config.getDate())
                        .child(Config.PROGRESS)
                        .setValue(str);
            }
        });
}

    class questionare{
            ArrayList<Boolean> answer;

        public questionare(String s) {
            for(int i=0;i<5;i++) {

            }
        }
    }
}
