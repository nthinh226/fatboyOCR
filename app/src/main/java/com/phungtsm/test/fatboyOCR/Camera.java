package com.phungtsm.test.fatboyOCR;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.camerakit.CameraKitView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Camera extends AppCompatActivity {
    private CameraKitView cameraKitView;
    private ImageView photoButton;
    private FrameLayout cameraLayout;
    private ViewFinderView viewFinderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraKitView = findViewById(R.id.camera);
        photoButton = findViewById(R.id.photoButton);
        photoButton.setOnClickListener(v -> {
            cameraKitView.captureImage((cameraKitView, capturedImage) -> {
                File savedPhoto = new File(getExternalFilesDir(null), "/camerakittest.jpg");
                Log.e("Status", "trang thai: "+savedPhoto.getPath());
                try {

                    Bitmap bitmap = BitmapFactory.decodeByteArray(capturedImage, 0, capturedImage.length);
                    Bitmap croppedBitmap = Bitmap.createBitmap(bitmap,
                            viewFinderView.getFrameRect().left,
                            viewFinderView.getFrameRect().top,
//                            viewFinderView.getFrameRect().right - viewFinderView.getFrameRect().left,
                            120,
                            120);
//                            viewFinderView.getFrameRect().height());

//                    String croppedBitmapBase64 = Utils.convertToBase64(croppedBitmap);
//                    Log.d("onCreate: ",croppedBitmapBase64);

                    Intent myIntent = new Intent(this, Cam_Captured.class);
                        myIntent.putExtra("pic", croppedBitmap);
                        startActivity(myIntent);

//                    FileOutputStream outputStream = new FileOutputStream(savedPhoto);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                    byte[] byteArray = stream.toByteArray();
//                    outputStream.write(byteArray);
//                    outputStream.close();
//                        Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
//                        myIntent.putExtra("basecode", savedPhoto.getPath());
//                        startActivity(myIntent);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            });
        });
        cameraLayout = findViewById(R.id.cameraLayout);
        viewFinderView = new ViewFinderView(getApplicationContext());
        cameraLayout.addView(viewFinderView, 1);
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
