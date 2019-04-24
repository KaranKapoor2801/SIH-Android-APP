package com.example.workerattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE_NAME="attendance1.db";
    private static final String TABLE_NAME="attendance";
    private static final int VERSION=1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
        SQLiteDatabase database=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NameOfLabor TEXT,TotalPayment INTEGER,TotalAttendance INTEGER,AttendanceOfLabor INTEGER,PayPerDay INTEGER,Category TEXT,SiteId TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(DataOfLabor dataOfLabor)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("NameOfLabor",dataOfLabor.getNameOfLabor());
        values.put("TotalPayment",dataOfLabor.getTotalPayment());
        values.put("TotalAttendance",dataOfLabor.getTotalAttendance());
        values.put("AttendanceOfLabor",dataOfLabor.getAttendanceOfLabor());
        values.put("PayPerDay",dataOfLabor.getPayPerDay());
        values.put("Category",dataOfLabor.getCategory());
        values.put("SiteId",dataOfLabor.getSiteId());
        return database.insert(TABLE_NAME, null, values) != -1;
    }
    public DataOfLabor getData(String NameOfLabor)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        DataOfLabor dataOfLabor=new DataOfLabor();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE NameOfLabor = '"+NameOfLabor+"'";
        Cursor cursor=database.rawQuery(query,null);
        while (cursor.moveToNext())
        {
            dataOfLabor.setNameOfLabor(cursor.getString(1));
            dataOfLabor.setTotalPayment(Double.parseDouble(cursor.getString(2)));
            dataOfLabor.setAttendanceOfLabor(Integer.parseInt(cursor.getString(4)));
            dataOfLabor.setPayPerDay(Double.parseDouble(cursor.getString(5)));
            dataOfLabor.setTotalAttendance(Integer.parseInt(cursor.getString(3)));
        }
        database.close();
        return dataOfLabor;
    }
    public ArrayList<DataOfLabor> getAllData(String siteID)
    {
        ArrayList<DataOfLabor> dataOfLaborArrayList=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();

        String query="SELECT * FROM "+TABLE_NAME+" WHERE SiteId = '"+siteID+"'";
        Cursor cursor=database.rawQuery(query,null);
        while (cursor.moveToNext())
        {
            DataOfLabor dataOfLabor=new DataOfLabor();
            dataOfLabor.setNameOfLabor(cursor.getString(1));
            dataOfLabor.setTotalPayment(Double.parseDouble(cursor.getString(2)));
            dataOfLabor.setAttendanceOfLabor(Integer.parseInt(cursor.getString(4)));
            dataOfLabor.setPayPerDay(Double.parseDouble(cursor.getString(5)));
            dataOfLabor.setTotalAttendance(Integer.parseInt(cursor.getString(3)));
            dataOfLabor.setCategory(cursor.getString(6));
            dataOfLabor.setSiteId(cursor.getString(7));
            dataOfLaborArrayList.add(dataOfLabor);
            Log.d("DATA", "addLabor: "+dataOfLabor.getTotalAttendance()+" "+dataOfLabor.getNameOfLabor());
        }
        database.close();
        return dataOfLaborArrayList;
    }
    public void deleteData(String NameOfLabor)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TABLE_NAME,"NameOfLabor = '"+ NameOfLabor+"'",null);
        database.close();
    }
    public void updateData(DataOfLabor dataOfLabor)
    {
        ContentValues values=new ContentValues();
        SQLiteDatabase database=this.getWritableDatabase();
        values.put("NameOfLabor",dataOfLabor.getNameOfLabor());
        values.put("TotalPayment",dataOfLabor.getTotalPayment());
        values.put("TotalAttendance",dataOfLabor.getTotalAttendance());
        values.put("AttendanceOfLabor",dataOfLabor.getAttendanceOfLabor());
        values.put("PayPerDay",dataOfLabor.getPayPerDay());
        values.put("Category","cat");
        database.update(TABLE_NAME,values,"NameOfLabor = '"+dataOfLabor.getNameOfLabor()+"'",null);
        database.close();
    }
}
