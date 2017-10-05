package com.fuady.ssa_app_redo1;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fuady on 8/7/2017.
 */

public class shareScheduleActivity extends AppCompatActivity{
    public String sharingString="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_qr_layout);
        ImageView imageView = (ImageView) findViewById(R.id.qrImage);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String myName = "No Name Entered";
        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        float brightness = (lp.screenBrightness<0)?1.0f:-1.0f;
        float brightness = 1.0f;
        lp.screenBrightness = brightness;
        getWindow().setAttributes(lp);
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("userName")));

            String line = reader.readLine();
            if(line != null) {
                myName = line;
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
        if(myName != "") {
            findFrees();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(myName+sharingString, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

    }
    public void findFrees(){
        ArrayList <String> fullScheduleArrayList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("allDaysSchedule")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
               fullScheduleArrayList.add(line);
            }
        } catch (IOException e) {
            System.out.println("cant find file");
            System.out.println("cant find file");
            System.out.println("cant find file");
            System.out.println("cant find file");
            e.printStackTrace();
        }
        for(int i = 0; i<fullScheduleArrayList.size();i++){
            if(fullScheduleArrayList.get(i).equals("Free") ||fullScheduleArrayList.get(i).equals("Lunch") ){
                sharingString+=',';
                sharingString+= i;
            }
        }
        System.out.println(sharingString);
    }


}
