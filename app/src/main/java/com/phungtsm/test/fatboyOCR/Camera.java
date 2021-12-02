package com.phungtsm.test.fatboyOCR;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;

import com.camerakit.CameraKitView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Camera extends AppCompatActivity {
    private static LruCache<String, Bitmap> mMemoryCache;
    public static String bMatTruoc =null;
    public static String bMatSau =null;

    private CameraKitView cameraKitView;
    private ImageView photoButtonCap;
    private FrameLayout cameraLayout;
    private ViewFinderView viewFinderView;
    private TextView txtTitle;
    private static TextView txtDir;
    private ImageView photoButtonClose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraKitView = findViewById(R.id.camera);
        photoButtonCap = findViewById(R.id.photoButtonCap);

        cameraLayout = findViewById(R.id.cameraLayout);
        viewFinderView = new ViewFinderView(getApplicationContext());
        cameraLayout.addView(viewFinderView, 1);
        txtTitle = findViewById(R.id.txtTitle);
        txtDir = findViewById(R.id.txtDir);
        photoButtonClose = findViewById(R.id.photoButtonClose);
        photoButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(getIntent().getExtras()!=null){
            txtDir.setText(getIntent().getStringExtra("chupmatsau"));
            if(getIntent().getIntExtra("status",0)==0){
                bMatTruoc = null;
            }
        }

        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

        if (MainActivity.Flag == 1 || MainActivity.Flag == 2) {
            txtTitle.setText("Chứng minh thư/Thẻ căn cước");
        } else if (MainActivity.Flag == 3) {
            txtTitle.setText("Hộ chiếu");
        }

        photoButtonCap.setOnClickListener(v -> {
            cameraKitView.captureImage((cameraKitView, capturedImage) -> {
                File savedPhoto = new File(getExternalFilesDir(null), "/camerakittest.jpg");
                Log.e("Status", "trang thai: " + savedPhoto.getPath());
                try {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(capturedImage, 0, capturedImage.length);

                    Log.d("onCreate: ", "left:" + viewFinderView.getFrameRect().left + "-TOP:" + viewFinderView.getFrameRect().top + "-Right:" +
                            viewFinderView.getFrameRect().right + "-Bottom" +
                            viewFinderView.getFrameRect().bottom +
                            "bitmapWidth:" + bitmap.getWidth() +
                            "bitmapHegiht:" + bitmap.getHeight() +
                            "width" + viewFinderView.getWidth() +
                            "height" + viewFinderView.getHeight()
                    );

                    Bitmap croppedBitmap = Bitmap.createBitmap(bitmap,
                            viewFinderView.getFrameRect().left,
                            viewFinderView.getFrameRect().top,
                            (viewFinderView.getFrameRect().right - viewFinderView.getFrameRect().left-100),
                            (viewFinderView.getFrameRect().bottom - viewFinderView.getFrameRect().top)-100);

                    if(getIntent().getExtras()!=null){
                        addBitmapToMemoryCache("imgmatsau",croppedBitmap);
                        String croppedBitmapBase64 = Utils.convertToBase64(croppedBitmap);
                        bMatSau = croppedBitmapBase64;
                        Log.d("onCreate1: ", croppedBitmapBase64);
                        Intent myIntent = new Intent(this, Cam_Captured.class);
                        myIntent.putExtra("status", 1);
                        myIntent.putExtra("imgmatsau", "imgmatsau");
                        startActivity(myIntent);
                    }else{
                        addBitmapToMemoryCache("imgmattruoc",croppedBitmap);
                        String croppedBitmapBase64 = Utils.convertToBase64(croppedBitmap);
                        bMatTruoc = croppedBitmapBase64;
                        Log.d("onCreate2: ", croppedBitmapBase64);
                        Intent myIntent = new Intent(this, Cam_Captured.class);
                        myIntent.putExtra("imgmattruoc", "imgmattruoc");
                        startActivity(myIntent);
                    }

                    FileOutputStream outputStream = new FileOutputStream(savedPhoto);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    outputStream.write(byteArray);
                    outputStream.close();



                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
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
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
