package com.phungtsm.test.fatboyOCR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class Cam_Captured extends AppCompatActivity {
    ImageView imagePreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_captured);

        imagePreview = findViewById(R.id.imagePreview);

        Intent intent = getIntent();
        Bitmap bitmap =(Bitmap) intent.getParcelableExtra("pic");
        imagePreview.setImageBitmap(bitmap);
    }
}