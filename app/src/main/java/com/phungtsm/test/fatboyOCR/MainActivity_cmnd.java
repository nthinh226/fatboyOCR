package com.phungtsm.test.fatboyOCR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_cmnd extends AppCompatActivity {
    Button btnTiepTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cmnd);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Camera.class);
                startActivity(intent1);
            }
        });
    }
}