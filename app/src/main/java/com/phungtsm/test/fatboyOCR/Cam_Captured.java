package com.phungtsm.test.fatboyOCR;

import static com.phungtsm.test.fatboyOCR.Camera.getBitmapFromMemCache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        Bitmap pic = getBitmapFromMemCache(intent.getStringExtra("mattruoc"));

        imagePreview.setImageBitmap(pic);
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
                Intent myIntent = new Intent(this, Camera.class);
                myIntent.putExtra("matsau","CHỤP MẶT SAU");
                startActivity(myIntent);
                break;
        }

    }
}