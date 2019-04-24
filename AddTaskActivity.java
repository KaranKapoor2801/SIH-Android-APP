package com.example.workerattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.workerattendance.Adapter.TaskAdapterEtc;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {
    String siteId;
    String constructorId;
    String siteType;
    String[] suggestedTasks;
    AutoCompleteTextView addTaskTextView;
    public static ArrayList<Task> taskList;
    ListView displayTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Bundle b = getIntent().getExtras();
        siteType = b.getString(Config.SITE_TYPE);
        siteId = b.getString(Config.SITE_ID);
        constructorId = b.getString(Config.CONSTRUCTOR);

        if(siteType == Config.STEEL) {
            suggestedTasks = new String[] {
                    "A", "B", "C", "D", "E", "Hi"
            };
            taskList = new ArrayList<>(Task.getSuggestedSteelTasks());
        } else {
            suggestedTasks = new String[] {
                    "A1", "B1", "C1", "D1", "E1"
            };

            taskList = new ArrayList<>(Task.getSuggestedCivilTasks());
        }
        displayTaskList = findViewById(R.id.taskList);
        addTaskTextView = findViewById(R.id.addTaskTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, suggestedTasks);
        addTaskTextView.setThreshold(1);
        addTaskTextView.setAdapter(adapter);
        displayTaskList.setAdapter(new TaskAdapterEtc(getApplicationContext(), taskList));
    }

    public void addNewTask(View view) {
        EditText taskNameField = findViewById(R.id.addTaskTextView);
        String taskName = taskNameField.getText().toString();
        if(Strings.isEmptyOrWhitespace(taskName)) {
            Toast.makeText(getApplicationContext(), "Enter valid task name", Toast.LENGTH_SHORT).show();
        } else {
            taskNameField.setText("");
            taskList.add(new Task(taskName, 5));
            displayTaskList.setAdapter(new TaskAdapterEtc(getApplicationContext(), taskList));
        }
    }

    public void submit(View view) {
        ArrayList<Task> finalList = new ArrayList<>();
        for(Task t: taskList) {
            if(t.getIsSelected()) {
                finalList.add(t);
            }
        }
        if(taskList.size()!=0) {
            FirebaseDatabase.getInstance().getReference().child(constructorId).child(siteId).child(Config.TASKS).setValue(finalList);
            Intent i = new Intent(getApplicationContext(), ClientActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "All tasks deselected. Select at-least one task", Toast.LENGTH_LONG).show();
        }
    }
}
