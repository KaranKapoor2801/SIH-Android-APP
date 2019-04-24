package com.example.workerattendance.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workerattendance.ClientActivity;
import com.example.workerattendance.Config;
import com.example.workerattendance.MainSiteActivity;
import com.example.workerattendance.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginTypeActivity extends AppCompatActivity
{
    String type;
    String email;
    LoggedInUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);
        user = new LoggedInUser(null);
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email = email.replace('.','_');
    }

    public void typeSelected(View view) {
        RelativeLayout clientLayout=findViewById(R.id.activity_select_client);
        RelativeLayout constructorLayout=findViewById(R.id.activitySelectConstructor);

        if(view.getId() ==  R.id.activity_select_client) {
            clientLayout.setBackgroundColor(getResources().getColor(R.color.gray_color));
            constructorLayout.setBackgroundColor(getResources().getColor(R.color.white_background));
            type = Config.Client;
        }
        if(view.getId() == R.id.activitySelectConstructor) {
            constructorLayout.setBackgroundColor( getResources().getColor(R.color.gray_color));
            clientLayout.setBackgroundColor(getResources().getColor(R.color.white_background));
            type = Config.CONSTRUCTOR;
        }
        user.type = type;
    }

    public void register(View view) {
        if(user.type!=null)
        {
            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child(Config.LOGIN_HISTORY)
                    .child(email)
                    .setValue(user);
            startNextActivity();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Select User Type",Toast.LENGTH_SHORT).show();
        }
    }

    private void startNextActivity() {
        Intent i;
        if(user.type == Config.CONSTRUCTOR) {
            i =new Intent(getApplicationContext(), MainSiteActivity.class);
            startActivity(i);
            finish();
        } else if(user.type == Config.Client){
            i =new Intent(getApplicationContext(), ClientActivity.class);
            startActivity(i);
            finish();
        } else {
            //Todo: Add supervisor

        }

    }
}