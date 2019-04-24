package com.example.workerattendance;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class MaterialChart extends AppCompatActivity {
    private BarChart barChart;
    private ArrayList<BarEntry> barEntries=new ArrayList<>();
    private ArrayList<Material> mMaterialData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_chart);
        init();
        setData();
    }
    public void addMaterial(View v)
    {
        final Dialog dialog=new Dialog(MaterialChart.this);
        dialog.setContentView(R.layout.add_material_dialog);
        final EditText materialName=dialog.findViewById(R.id.dialogNameOfLabor);
        final EditText materialQuantity=dialog.findViewById(R.id.dialogPayPerDay);
        Button button=(Button)dialog.findViewById(R.id.dialogMaterialOkBt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStringNotNull(materialName.getText().toString())&&checkStringNotNull(materialName.getText().toString()))
                {
                    Material data=new Material(materialName.getText().toString(),Integer.parseInt(materialQuantity.getText().toString()));
                    mMaterialData.add(data);
                    dialog.dismiss();
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Check Filed",Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();

    }
    public void init()
    {
        mMaterialData=new ArrayList<>();
        barChart=(BarChart) findViewById(R.id.activity_chart);
    }
    class MyXAxisData implements IAxisValueFormatter {
        String []xAxisData;
        MyXAxisData(String[]values)
        {
            xAxisData=values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return xAxisData[(int)value];
        }
    }
    public boolean checkStringNotNull(String check)
    {
        return !check.equals("");
    }
    public void setData()
    {
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setPinchZoom(false);
        barChart.setMaxVisibleValueCount(50);
        barChart.setDrawGridBackground(true);
        barEntries.add(new BarEntry(1,40f));
        barEntries.add(new BarEntry(2,44f));
        barEntries.add(new BarEntry(3,30f));
        barEntries.add(new BarEntry(4,36f));
        BarDataSet mBarDataSet=new BarDataSet(barEntries,"Material Name");
        mBarDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(mBarDataSet);
        data.setBarWidth(0.9f);
        barChart.setData(data);
        String []XAxisD=new String[]{"Mud1","Mud2","Mud3","Mud4"};
    }
}
