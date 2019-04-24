package com.example.workerattendance;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.workerattendance.Adapter.ListOfAvaliableClientSiteAdapter;
import com.example.workerattendance.models.DataOfSite;
import com.example.workerattendance.models.DataUpdateFromClientSite;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ClientActivity extends AppCompatActivity {
    ListOfAvaliableClientSiteAdapter adapter;
    ArrayList<DataUpdateFromClientSite> mData=new ArrayList<>();
    ListView mSiteListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        mSiteListView = findViewById(R.id.listViewAvailableSiteClientActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

    private void populateList() {
        FirebaseDatabase.getInstance().getReference().
                child(Config.SITE_DATA).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot snapshots: it) {
                    Iterable<DataSnapshot> it2 = snapshots.getChildren();
                    for (DataSnapshot snapshot: it2){
                        String emailOfClient = snapshot.child(Config.EMAIL_OF_CLIENT).getValue().toString();
                        String auth = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        Log.d("Match", auth + " "+emailOfClient);
                        if(emailOfClient.compareTo(auth) == 0) {
                            mData.add(new DataUpdateFromClientSite(
                                    snapshot.child(Config.PROJECT_TYPE).getValue().toString(),
                                    snapshot.child(Config.SITE_NAME).getValue().toString(),
                                    snapshot.child(Config.SITE_ID).getValue().toString(),
                                    snapshot.child(Config.EMAIL_OF_CONSTRUCTOR).getValue().toString(),
                                    snapshot.child(Config.EMAIL_OF_CLIENT).getValue().toString(),
                                    snapshot.child(Config.CURRENT_DATE).getValue().toString(),
                                    snapshot.child(Config.SITE_ENGINEER_ID).getValue().toString()
                            ));
                        }
                    }
                }
                addAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    private void addAdapter() {
        adapter = new ListOfAvaliableClientSiteAdapter(this, mData);
        mSiteListView.setAdapter(adapter);
    }

    public void createNewSite(View view) {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_site_client);
        final RadioButton bt=dialog.findViewById(R.id.dialogClienCivilSelect);
        final EditText siteName=dialog.findViewById(R.id.dialogClientSiteName);
        final EditText emailOfConstructor=dialog.findViewById(R.id.dialogEmailOfConstructor);
        Button button=(Button)dialog.findViewById(R.id.dialogClientSiteDataSubmit);
        final EditText engineerId=dialog.findViewById(R.id.dialogClientSiteEngineerId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectType="";
                if (bt.isChecked())
                    projectType ="civil";
                else
                    projectType="steel";

                String name = siteName.getText().toString();
                String emailConstructor = emailOfConstructor.getText().toString();
                String engineerEmail = engineerId.getText().toString();
                System.out.println("hey");
                if(!Strings.isEmptyOrWhitespace(projectType)
                        && checkStringNotNull(name)
                        && checkStringNotNull(emailConstructor)
                        && checkStringNotNull(engineerEmail))
                {
                    getSiteIdFromFirebase(siteName, emailOfConstructor, projectType, engineerId,dialog);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    private void getSiteIdFromFirebase(final EditText siteName, final EditText emailOfConstructor, final String finalProjectType,final EditText engineerId, final Dialog dialog) {
        FirebaseDatabase.getInstance().getReference().child(Config.SITE_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = emailOfConstructor.getText().toString().replace('.','_');
                String enggEmail = engineerId.getText().toString().replace('.','_');

                int currentId = Integer.parseInt(dataSnapshot.getValue().toString());
                currentId++;
                updateSiteId(currentId);
                final DataUpdateFromClientSite dataFromClientSite=new DataUpdateFromClientSite();
                dataFromClientSite.setSiteId(""+currentId);
                dataFromClientSite.setEmailOfClient(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                dataFromClientSite.setEmailOfConstructor(email);
                dataFromClientSite.setSiteEngineerId(enggEmail);
                dataFromClientSite.setProjectType(finalProjectType);
                dataFromClientSite.setSiteName(siteName.getText().toString());
                dataFromClientSite.setCurrentDate(Config.getDate());
                mData.add(dataFromClientSite);
                dialog.dismiss();
                addThisSiteToFirebase(dataFromClientSite);
                Intent i = new Intent(getApplicationContext(), AddTaskActivity.class);
                i.putExtra(Config.SITE_ID, dataFromClientSite.getSiteId());
                i.putExtra(Config.CONSTRUCTOR, dataFromClientSite.getEmailOfConstructor());
                i.putExtra(Config.SITE_TYPE, dataFromClientSite.getProjectType());
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateSiteId(int curId) {
        FirebaseDatabase.getInstance().getReference()
                .child(Config.SITE_ID)
                .setValue(curId);
    }

    private void addThisSiteToFirebase(DataUpdateFromClientSite dataFromClientSite) {
        FirebaseDatabase.getInstance().getReference()
                .child(Config.SITE_DATA)
                .child(dataFromClientSite.getEmailOfConstructor())
                .child(dataFromClientSite.getSiteId())
                .setValue(dataFromClientSite);
//        FirebaseDatabase.getInstance().getReference()
//                .child(dataFromClientSite.getEmailOfClient())
//                .child(dataFromClientSite.getSiteId())
//                .setValue(dataFromClientSite);
//        FirebaseDatabase.getInstance().getReference()
//                .child(dataFromClientSite.getSiteEngineerId())
//                .child(dataFromClientSite.getSiteId())
//                .setValue(dataFromClientSite);
    }

    public boolean checkStringNotNull(String check)
    {
        return !check.equals("");
    }
}
