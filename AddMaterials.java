package com.example.workerattendance;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AddMaterials extends AppCompatActivity {

    public static Material[] materialArray;
    private ArrayList<Pair <Material, View> > materialContainerPair;
    public static String siteId;
    public static String todaysDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materials);
        FirebaseApp.initializeApp(getApplicationContext());
        Material cement = new Material("Cement", 5);
        materialArray = new Material[] {cement};
        todaysDate = "26022019";
        materialContainerPair = new ArrayList<>();
        buildMaterialContainerPair();
        siteId = "h10";

//         Must develop edit texts with id same as material name


    }

    private void buildMaterialContainerPair() {
        for(Material material: materialArray) {
            materialContainerPair.add(new Pair(material, findViewById(getResId(material.getMaterialName(), R.id.class))));
        }
    }

    public void done(View view) {
        if(view.getId() == R.id.done) {
            updateQuantities();
            updateInDatabase();
            fetch();
//            updateReport();
        }
    }

    private void fetch() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database
                .getReference("Updates")
                .child(siteId)
                .child(todaysDate)
                .child(Config.UPDATES)
                .child(Config.MATERIAL);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();
                Log.d("Output", s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateReport() {

    }

    private void updateInDatabase() {
        try {


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database
                    .getReference("Updates")
                    .child(siteId)
                    .child(todaysDate)
                    .child(Config.UPDATES)
                    .child(Config.MATERIAL);
            myRef.setValue(getJsonArray(materialArray));
        } catch (JSONException e) {

         //    * Karle bhai catch exception


        }
    }

    private String getJsonArray(Material[] materialArray) throws JSONException {
        JSONArray array = new JSONArray();
        for (Material material : materialArray) {
            JSONObject obj = new JSONObject();
            obj.put(Config.MATERIAL_NAME,material.getMaterialName());
            obj.put(Config.MATERIAL_QUANTITY, material.getMaterialQuantity());
            array.put(obj);
        }
        return array.toString();
    }

    private void updateQuantities() {

      //   * Take value from corresponding input field and add to existing quantities


        for(Pair pair: materialContainerPair) {
            Material curMat = (Material) pair.first;
            EditText additionalQuantityField = (EditText) pair.second;
            int additionalQuantity = Integer.parseInt(additionalQuantityField.getText().toString());
            curMat.addQuantity(additionalQuantity);
        }
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
