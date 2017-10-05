package com.fuady.ssa_app_redo1;

/**
 * Created by fuady on 6/20/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.EnumMap;
import java.util.Map;

import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.fuady.ssa_app_redo1.R.layout.barcode;
import static java.security.AccessController.getContext;

public class barcodeActivity extends AppCompatActivity{

    private String codeFormat, codeContent;
    private TextView formatTxt, contentTxt;
    private ImageView hasBarcodeImage;
    public String barcodeTxt = "";
    public EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(barcode);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (EditText) findViewById(R.id.scan_content);
        hasBarcodeImage = (ImageView) findViewById(R.id.barcodeImage);
        editText = (EditText)findViewById(R.id.scan_content);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        float brightness = (lp.screenBrightness<0)?1.0f:-1.0f;
        float brightness = 1.0f;
        lp.screenBrightness = brightness;
        getWindow().setAttributes(lp);
       /* try {
            OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput(
                    "barcodeTxt", Context.MODE_PRIVATE));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//fix make barcode*/
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("barcodeTxt")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                barcodeTxt = line;
                editText.setText(barcodeTxt);
                System.out.println("barcodeTxt = " + barcodeTxt);
            }
        }
        catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
        if(barcodeTxt != "") {
            actualMakeBarcode(barcodeTxt);
        }else{
            // hasBarcodeImage.setImage(barcode);
            System.out.println("generating barcode failed");
        }


    }





    public void scanNow(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(this.getString(R.string.scan_bar_code));
        //integrator.setResultDisplayDuration(0);
        //integrator.setWide();

        // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }

    public void deleteBarcode(View view){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput(
                    "barcodeTxt", Context.MODE_PRIVATE));
            writer.close();
            editText.setText("Barcode Cleared");
        } catch (IOException e) {
            e.printStackTrace();
        }//fix make barcode
        ImageView bImage = (ImageView)findViewById(R.id.barcodeImage);
        //int id = getResources().getIdentifier("yourpackagename:drawable/" + StringGenerated, null, null);
        bImage.setImageDrawable(getResources().getDrawable(R.drawable.barcode));
        //bImage.setImageResource(id);
    }

    public void addTextCode(View view){
        String code = editText.getText().toString();
        if (code.length() == 12) {
            //BarcodeTools.makeBarcodeImage(this, code);
            // fillData(code);
            actualMakeBarcode(code);
            System.out.println("passed");
            try {
                OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput(
                        "barcodeTxt", Context.MODE_PRIVATE));
                writer.write(code);
                System.out.println(code + " was written");
                writer.close();
                editText.setText("Barcode Added");
            } catch (IOException e) {
                e.printStackTrace();
            }//fix make barcode
        } else {
            editText.setText("Incorrect Barcode Length");
            System.out.println("failed because code.length() = " + code.length() + "code = " + code);
        }

    }
    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String code = "";
        if (scanningResult != null) {
            //we have a result
            codeContent = scanningResult.getContents();
            codeFormat = scanningResult.getFormatName();

            // display it on screen
            formatTxt.setText("FORMAT: " + codeFormat);
            contentTxt.setText("CONTENT: " + codeContent);
            if(codeContent!= null) {
                code = codeContent.toString();
            }
            if (code.length() == 12) {
                //BarcodeTools.makeBarcodeImage(this, code);
                // fillData(code);
                actualMakeBarcode(code);
                System.out.println("passed");
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput(
                            "barcodeTxt", Context.MODE_PRIVATE));
                    writer.write(code);
                    System.out.println(code + " was written");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }//fix make barcode
            } else {
                System.out.println("failed because code.length() = " + code.length() + "code = " + code);
            }
            // Intent myIntent = new Intent(getApplicationContext(), BarcodeExampleActivity.class);
            // startActivity(myIntent);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    void actualMakeBarcode(String code) {
        String barcode_data = code;

        // barcode image
        Bitmap bitmap = null;
        ImageView iv = hasBarcodeImage;

        try {

            bitmap = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_39, 600, 300);
            iv.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    /**************************************************************
     * getting from com.google.zxing.client.android.encode.QRCodeEncoder
     *
     * See the sites below
     * http://code.google.com/p/zxing/
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
     */

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
    public void viewFriends(View view){
        Intent myIntent = new Intent(getApplicationContext(), friendActivity.class);
       // finish();
        startActivity(myIntent);
    }
    public void viewOthers(View view){
        Intent myIntent = new Intent(getApplicationContext(), otherActivity.class);
        // finish();
        startActivity(myIntent);
    }
    public void viewSchedule(View view){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        //finish();
        startActivity(myIntent);
        //setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
//        if (showingPersonScreen) {
       // back(null);
        //      } else {
               super.onBackPressed();
        //  }
    }



}
