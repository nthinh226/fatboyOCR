package com.phungtsm.test.fatboyOCR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Tutorial extends AppCompatActivity {
    Button btnTiepTuc;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        layout = findViewById(R.id.layout_tutorial);
        int Flag = MainActivity.Flag;
        if(Flag == 1 || Flag == 2){
            layout.setBackgroundResource(R.drawable.cmt_background);
        }
        if(Flag == 3){
            layout.setBackgroundResource(R.drawable.pp_png);
        }
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