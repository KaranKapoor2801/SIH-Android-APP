package com.example.workerattendance;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Task {
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    String taskName;
    int duration;
    boolean isSelected;
    int perDayCost;
    public String reason;
    public double percentage;


    public Task(String taskName, int duration) {
        this.taskName = taskName;
        this.duration = duration;
        this.isSelected = true;
        this.perDayCost = 500;
    }

    public Task(DataSnapshot taskSnapshot) {
        this.taskName = taskSnapshot.getKey();
        this.reason = taskSnapshot.child(Config.REASON).getValue().toString();
        this.percentage = Double.parseDouble(taskSnapshot.child(Config.PERCENTAGE).getValue().toString());
    }

    public Task(String taskName, int duration, boolean isSelected) {
        this.taskName = taskName;
        this.duration = duration;
        this.isSelected = isSelected;
    }

    public static ArrayList<Task> getSuggestedSteelTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        Task t1= new Task("A", 5);
        taskList.add(t1);
        Task t2= new Task("B", 10);
        taskList.add(t2);
        Task t3= new Task("C", 300);
        taskList.add(t3);
        Task t4= new Task("D", 100);
        taskList.add(t4);
        return taskList;
    }

    public static ArrayList<Task> getSuggestedCivilTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        Task t1= new Task("A1", 5);
        taskList.add(t1);
        Task t2= new Task("B1", 10);
        taskList.add(t2);
        Task t3= new Task("C1", 300);
        taskList.add(t3);
        Task t4= new Task("D1", 100);
        taskList.add(t4);
        return taskList;
    }
}
