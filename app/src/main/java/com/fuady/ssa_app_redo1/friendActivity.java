package com.fuady.ssa_app_redo1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.zxing.integration.android.IntentIntegrator;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by fuady on 7/24/2017.
 */

public class friendActivity extends AppCompatActivity /*implements ZXingScannerView.ResultHandler*/ {
    private ZXingScannerView mScannerView;
    private String inputString = "";
    public String addedFriend = "";
    public ArrayList<String> myFriends = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);

    }
    @Override
    protected void onResume(){
        super.onResume();
        setupListView();
    }


    public void viewBarcode(View view) {
        Intent myIntent = new Intent(getApplicationContext(), barcodeActivity.class);
        //finish();
        startActivity(myIntent);
    }

    public void viewOthers(View view) {
        Intent myIntent = new Intent(getApplicationContext(), otherActivity.class);
        // finish();
        startActivity(myIntent);
    }

    public void viewSchedule(View view) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
       // finish();
        startActivity(myIntent);
    }
    public void Share(View view){
        String myName = "No Name Entered";

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
        if(myName.equals("No Name Entered")){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("Please Enter Your Name");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT/* | InputType.TYPE_TEXT_VARIATION_PASSWORD*/);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    inputString = input.getText().toString();
                    try {
                        OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                                "userName", Context.MODE_PRIVATE));
                        writer.write(inputString);
                        System.out.println("writing username"+ inputString);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent myIntent = new Intent(getApplicationContext(), shareScheduleActivity.class);
                    startActivity(myIntent);

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        }else{
            Intent myIntent = new Intent(getApplicationContext(), shareScheduleActivity.class);
            startActivity(myIntent);
        }
        /*
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        */


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        //TextView tv = (TextView)findViewById(R.id.textView3);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            if(scanningResult.getContents() != null) {
                addedFriend = scanningResult.getContents().toString();

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

            }
        }
    }
    public void setupListView() {
        final ArrayList<String> myFriendsNames = new ArrayList<>();
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
        for(int i =0; i< myFriends.size();i++){
            String[] myFriendsInfo = myFriends.get(i).split(",");
            myFriendsNames.add(myFriendsInfo[0]);
        }
        ListView listDays = (ListView) findViewById(R.id.listView2);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, myFriendsNames);
        // Set The Adapter
        listDays.setAdapter(arrayAdapter);
// register onClickListener to handle click events on each item
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, final int position, long arg3)
            {

               /* AlertDialog.Builder builder = new AlertDialog.Builder(friendActivity.this);
                builder.setTitle("Scan Result");
                builder.setMessage("test");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                */

                AlertDialog.Builder builder1 = new AlertDialog.Builder(friendActivity.this);
                //AlertDialog.Builder builder1 = new AlertDialog.Builder(Context context);
                builder1.setMessage("Do you really want to remove " + myFriendsNames.get(position)+ " from your friends?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myFriends.remove(position);
                                myFriendsNames.remove(position);
                                try {
                                    OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                                            "friendFile", Context.MODE_PRIVATE));
                                    for(int i = 0; i<myFriends.size();i++){
                                        writerTwo.write(myFriends.get(i) + '\n');
                                    }
                                    writerTwo.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                setupListView();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

    }
}




