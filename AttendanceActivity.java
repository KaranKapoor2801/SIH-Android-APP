package com.example.workerattendance;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.workerattendance.Adapter.AttendanceAdapter;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {
    private ListView mAttendanceListView;
    AttendanceAdapter mAttendanceAdapter;
    SQLiteHelper helper;
    String siteId;
    private ArrayList<DataOfLabor> mArrayOfData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        init();
        attendanceManage();
        listAdapterManage();
    }

    private void listAdapterManage() {
        mAttendanceAdapter=new AttendanceAdapter(getApplicationContext(),mArrayOfData,helper);
        mAttendanceListView.setAdapter(mAttendanceAdapter);
    }

    private void init() {
        helper=new SQLiteHelper(this);
        mAttendanceListView=(ListView) findViewById(R.id.listViewLaborAttendanceActivity);
        siteId = getIntent().getExtras().getString(Config.SITE_ID);
    }
    private void attendanceManage()
    {
        mArrayOfData=helper.getAllData(siteId);
//        Log.d("Data", "attendanceManage: "+mArrayOfData.size()+" "+mArrayOfData.get(0).getNameOfLabor()+" "+mArrayOfData.get(1).getNameOfLabor());
    }

    public void addLabor(View view) {
        final Dialog dialog=new Dialog(AttendanceActivity.this);
        dialog.setContentView(R.layout.attendance_dialog);
        final EditText nameOfLabor=dialog.findViewById(R.id.dialogNameOfLabor);
        final EditText payPerDay=dialog.findViewById(R.id.dialogPayPerDay);
        final EditText category=dialog.findViewById(R.id.dialogCategory);
        Button button=(Button)dialog.findViewById(R.id.dialogOkBt);
        final DataOfLabor dataOfLabor=new DataOfLabor();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStringNotNull(nameOfLabor.getText().toString())&&checkStringNotNull(payPerDay.getText().toString())&&checkStringNotNull(category.getText().toString()))
                {
                    dataOfLabor.setTotalPayment(0);
                    dataOfLabor.setNameOfLabor(nameOfLabor.getText().toString());
                    dataOfLabor.setAttendanceOfLabor(0);
                    dataOfLabor.setTotalAttendance(0);
                    dataOfLabor.setPayPerDay(Integer.parseInt(payPerDay.getText().toString()));
                    dataOfLabor.setCategory(category.getText().toString());
                    dataOfLabor.setSiteId(siteId);
                    helper.insertData(dataOfLabor);
                    mArrayOfData.add(dataOfLabor);
                    mAttendanceAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Check Filed",Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
    public boolean checkStringNotNull(String check)
    {
        return !check.equals("");
    }

}
