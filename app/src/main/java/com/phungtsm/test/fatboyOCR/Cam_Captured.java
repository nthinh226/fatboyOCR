package com.phungtsm.test.fatboyOCR;

import static com.phungtsm.test.fatboyOCR.Camera.getBitmapFromMemCache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Cam_Captured extends AppCompatActivity implements View.OnClickListener {
    Button btnTiepTucMS;
    ImageView imagePreview;
    TextView txtChupLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_captured);

        imagePreview = findViewById(R.id.imagePreview);
        btnTiepTucMS = findViewById(R.id.btnTiepTucMS);
        txtChupLai = findViewById(R.id.txtChupLai);
        Intent intent = getIntent();
        if(intent.getIntExtra("status",0)==1){
            Bitmap pic = getBitmapFromMemCache(intent.getStringExtra("imgmatsau"));
            imagePreview.setImageBitmap(pic);
        }else{
            Bitmap pic = getBitmapFromMemCache(intent.getStringExtra("imgmattruoc"));
            imagePreview.setImageBitmap(pic);
        }



        txtChupLai.setOnClickListener(this);
        btnTiepTucMS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtChupLai:
                finish();
                break;
            case R.id.btnTiepTucMS:
                if(getIntent().getIntExtra("status",0)==0){
                    Intent myIntent = new Intent(this, Camera.class);
                    myIntent.putExtra("chupmatsau","CHỤP MẶT SAU");
                    myIntent.putExtra("status",1);
                    startActivity(myIntent);
                }else{
                    save(Camera.bMatSau);
                    startActivity(new Intent(Cam_Captured.this,MainActivity2.class));
                }
                break;
        }

    }
    public void save(String test) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("basecode.txt", MODE_PRIVATE);
            fos.write(test.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "basecode.txt", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}