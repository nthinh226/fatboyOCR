package com.phungtsm.test.fatboyOCR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCMND, btnCCCD, btnHC;
    public static int Flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCMND = findViewById(R.id.btnCMND);
        btnCCCD = findViewById(R.id.btnCCCD);
        btnHC = findViewById(R.id.btnHC);

        btnCMND.setOnClickListener(this);
        btnCCCD.setOnClickListener(this);
        btnHC.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCMND:
                Flag = 1;
                Intent intent1 = new Intent(this, Tutorial.class);
                startActivity(intent1);
                break;
            case R.id.btnCCCD:
                Intent intent2 = new Intent(this, Tutorial.class);
                startActivity(intent2);
                break;
            case R.id.btnHC:
                Flag = 3;
                Intent intent3 = new Intent(this, Tutorial.class);
                startActivity(intent3);
                break;
        }
    }
}