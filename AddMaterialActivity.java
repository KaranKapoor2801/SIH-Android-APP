package com.example.workerattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.workerattendance.Adapter.MaterialAdapter;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddMaterialActivity extends AppCompatActivity {
    String siteId;
    String constructorId;
    String siteType;
    String[] suggestedMaterials;
    AutoCompleteTextView addMaterialTextView;
    public static ArrayList<MaterialSahil> materialList;
    ListView displayMaterialList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);
        siteType = Config.STEEL;
        siteId = "abcde";
        constructorId = "ghijkl";

        if(siteType == Config.STEEL) {
            suggestedMaterials = new String[] {
                    "A", "B", "C", "D", "E", "Hi"
            };
            materialList = new ArrayList<>(MaterialSahil.getSuggestedSteelMaterials());
        } else {
            suggestedMaterials = new String[] {
                    "A1", "B1", "C1", "D1", "E1"
            };

            materialList = new ArrayList<>(MaterialSahil.getSuggestedCivilMaterials());
        }
        displayMaterialList = findViewById(R.id.materialList);
        addMaterialTextView = findViewById(R.id.addMaterialTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, suggestedMaterials);
        addMaterialTextView.setThreshold(1);
        addMaterialTextView.setAdapter(adapter);
        displayMaterialList.setAdapter(new MaterialAdapter(getApplicationContext(), materialList));
    }

    public void addNewMaterial(View view) {
        EditText materialNameField = findViewById(R.id.addMaterialTextView);
        String materialName = materialNameField.getText().toString();
        if(Strings.isEmptyOrWhitespace(materialName)) {
            Toast.makeText(getApplicationContext(), "Enter valid material name", Toast.LENGTH_SHORT).show();
        } else {
            materialNameField.setText("");
            materialList.add(new MaterialSahil(materialName, 5));
            displayMaterialList.setAdapter(new MaterialAdapter(getApplicationContext(), materialList));
        }
    }

    public void submit(View view) {
        ArrayList<MaterialSahil> finalList = new ArrayList<>();
        for(MaterialSahil t: materialList) {
            if(t.isMaterialSelected()) {
                finalList.add(t);
            }
        }
        if(materialList.size()!=0) {
            FirebaseDatabase.getInstance().getReference().child(constructorId).child(siteId).child(Config.MATERIAL).setValue(finalList);
        }
    }
}
