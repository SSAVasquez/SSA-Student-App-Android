package com.fuady.ssa_app_redo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by fuady on 9/15/2017.
 */

public class refreshBulletin extends AsyncTask<Object, Object, Void> {
    static Bitmap bulletinImage;
    @Override
    protected Void doInBackground(Object... params) {
        bulletinImage = null;
        URL url;
        try {
            url = new URL("https://sites.google.com/site/ssamobilehelper/schooldaysinfo/Daily%20Bulletin.png?attredirects=0&d=1");
            bulletinImage = getBitmapFromURL(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static void main(String[]args) throws IOException {

        //int[][]schedule = readSchedule(strURL);
        // System.out.println(Arrays.deepToString(schedule));
    }




    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }
}


