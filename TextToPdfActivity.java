package com.example.workerattendance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TextToPdfActivity extends AppCompatActivity {

    private static final int STORAGE_CODE=1000;
    String s;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_pdf);
        s = getIntent().getExtras().getString(Config.PDF_VALUE);
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, STORAGE_CODE);
            }
            else{
                    savePdf();
            }
        }

    }

    private void savePdf() {
        Document mDoc=new Document();
        String mFileName=new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath= Environment.getExternalStorageDirectory()+"/"+mFileName+".pdf";
        try{
            PdfWriter.getInstance(mDoc,new FileOutputStream(mFilePath));
            mDoc.open();
            String mText=s;
            mDoc.addAuthor("Ishapreet Kaur");
            mDoc.add(new Paragraph(mText));
            mDoc.close();
            file=new File(mFilePath);
//            Uri path = FileProvider.getUriForFile(this, "com.restart.sharingdata", file);
//            Intent shareIntent = new Intent();
//            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.putExtra(Intent.EXTRA_TEXT, "This is one image I'm sharing.");
//            shareIntent.putExtra(Intent.EXTRA_STREAM, path);
//            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            shareIntent.setType("image/*");
//            startActivity(Intent.createChooser(shareIntent, "Share..."));

            Toast.makeText(this, mFileName+".pdf", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Not Possible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case STORAGE_CODE:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    savePdf();
                }
                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
