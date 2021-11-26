package com.phungtsm.test.fatboyOCR;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Utils {

    public static String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
}
