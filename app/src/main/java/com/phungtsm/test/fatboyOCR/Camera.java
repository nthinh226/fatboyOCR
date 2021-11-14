package com.phungtsm.test.fatboyOCR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.camerakit.CameraKitView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Camera extends AppCompatActivity {
    private CameraKitView cameraKitView;
    private ImageView photoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraKitView = findViewById(R.id.camera);
        photoButton = findViewById(R.id.photoButton);
        photoButton.setOnClickListener(photoOnClickListener);
    }
    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private View.OnClickListener photoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                    File savedPhoto = new File(getExternalFilesDir(null), "/camerakittest.jpg");
                    Log.e("Status", "trang thai: "+savedPhoto.getPath());
                    try {
                        FileOutputStream outputStream = new FileOutputStream(savedPhoto);
                        outputStream.write(capturedImage);
                        outputStream.close();
//                        Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
//                        myIntent.putExtra("basecode", savedPhoto.getPath());
//                        startActivity(myIntent);
                    } catch (java.io.IOException e) {
                        e.printStackTrace();

                    }
                }
            });

        }
    };

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}