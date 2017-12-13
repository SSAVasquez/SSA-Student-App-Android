package com.fuady.ssa_app_redo1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by fuady on 8/7/2017.
 */

public class shareScheduleActivity extends AppCompatActivity{
    public String sharingString="";
    public String addedFriend = "";
    public String myName = "";
    public ArrayList<String> myFriends = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_qr_layout);
        ImageView imageView = (ImageView) findViewById(R.id.qrImage);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        myName = "No Name Entered";
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
            EditText editText = (EditText) findViewById(R.id.nameEditText);
            editText.setText(myName);
            findFrees();
            TextView textView = (TextView)findViewById(R.id.myCodeText);
            textView.setText(myName+sharingString);
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(myName+sharingString, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }

        }
        myFriends.clear();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("friendFile")));
            int lineCounter = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                myFriends.add(line);
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
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
    public void enterFriendCode(View view){
        EditText editText = (EditText)findViewById(R.id.codeEditText);
        if( editText.getText().toString() != null) {

            addedFriend = editText.getText().toString();
            myFriends.add(addedFriend);
           /* try {
                OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                        "friendFile", Context.MODE_PRIVATE));

                writerTwo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            try {
                OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                        "friendFile", Context.MODE_PRIVATE));
                for (int i = 0; i < myFriends.size(); i++) {
                    writerTwo.write(myFriends.get(i) + '\n');
                    System.out.println(myFriends.get(i) + '\n');
                }
                writerTwo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] myFriendsInfo = addedFriend.split(",");
            Toast.makeText(getApplicationContext(), myFriendsInfo[0] + " was added to your friends.", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(getApplicationContext(), friendActivity.class);
            startActivity(myIntent);
        }

    }
    public void enterName(View view){
        EditText editText = (EditText) findViewById(R.id.nameEditText);
        if( editText.getText().toString() != null) {
            myName = editText.getText().toString();
            try {
                OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                        "userName", Context.MODE_PRIVATE));
                writer.write(myName);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), myName + " will now appear as your name.", Toast.LENGTH_LONG).show();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            ImageView imageView = (ImageView) findViewById(R.id.qrImage);
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
    public void copyCode(View view){
        TextView textView = (TextView)findViewById(R.id.myCodeText);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", textView.getText());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Code Copied to Clipboard",
                Toast.LENGTH_LONG).show();
    }

    public void addFriends(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan QR Code");
        //integrator.setResultDisplayDuration(0);
        //  integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        //TextView tv = (TextView)findViewById(R.id.textView3);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            if(scanningResult.getContents() != null) {
                addedFriend = scanningResult.getContents().toString();
                String[] myFriendsInfo = addedFriend.split(",");

                myFriends.add(addedFriend);
           /* try {
                OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                        "friendFile", Context.MODE_PRIVATE));

                writerTwo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

                try {
                    OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                            "friendFile", Context.MODE_PRIVATE));
                    for (int i = 0; i < myFriends.size(); i++) {
                        writerTwo.write(myFriends.get(i) + '\n');
                        System.out.println(myFriends.get(i) + '\n');
                    }
                    writerTwo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), myFriendsInfo[0] + " was added to your friends.", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getApplicationContext(), friendActivity.class);
                startActivity(myIntent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
