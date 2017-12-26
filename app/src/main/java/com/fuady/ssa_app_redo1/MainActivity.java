package com.fuady.ssa_app_redo1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.icu.text.MessageFormat;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.TUESDAY;
/* Notes
    appbg3 should be used on larger devices
    appbg4 should be used on smaller devices
 */
public class MainActivity extends AppCompatActivity /*implements View.OnClickListener */{

    public boolean isTuesday = false;
    public ArrayList<String> myFrees = new ArrayList<>();





    public static int[][] fullSchedule = new int[][]{
            {0,8,29,2017,2,0,1},
            {1,8,30,2017,3,1,0},
            {2,8,31,2017,4,2,0},
            {3,9,1,2017,5,3,0},
            {4,9,5,2017,2,4,0},
            {5,9,6,2017,3,5,0},
            {6,9,7,2017,4,6,0},
            {7,9,8,2017,5,7,0},
            {8,9,11,2017,1,8,0},
            {9,9,12,2017,2,1,0},
            {10,9,13,2017,3,2,0},
            {11,9,14,2017,4,3,0},
            {12,9,15,2017,5,4,0},
            {13,9,18,2017,1,5,0},
            {14,9,19,2017,2,6,0},
            {15,9,20,2017,3,7,0},
            {16,9,22,2017,5,8,0},
            {17,9,25,2017,1,1,0},
            {18,9,26,2017,2,2,0},
            {19,9,27,2017,3,3,0},
            {20,9,28,2017,4,4,0},
            {21,9,29,2017,5,5,0},
            {22,10,2,2017,1,6,0},
            {23,10,3,2017,2,7,0},
            {24,10,4,2017,3,8,0},
            {25,10,5,2017,4,1,0},
            {26,10,6,2017,5,2,0},
            {27,10,10,2017,2,3,0},
            {28,10,11,2017,3,0,2},
            {29,10,12,2017,4,4,0},
            {30,10,13,2017,5,5,0},
            {31,10,16,2017,1,6,0},
            {32,10,17,2017,2,7,0},
            {33,10,18,2017,3,8,0},
            {34,10,19,2017,4,1,0},
            {35,10,20,2017,5,2,0},
            {36,10,23,2017,1,3,0},
            {37,10,24,2017,2,4,0},
            {38,10,25,2017,3,5,0},
            {39,10,26,2017,4,6,0},
            {40,10,27,2017,5,7,0},
            {41,10,30,2017,1,8,0},
            {42,10,31,2017,2,1,0},
            {43,11,1,2017,3,2,0},
            {44,11,2,2017,4,3,0},
            {45,11,3,2017,5,4,0},
            {46,11,6,2017,1,5,0},
            {47,11,7,2017,2,6,0},
            {48,11,8,2017,3,7,0},
            {49,11,9,2017,4,8,0},
            {50,11,10,2017,5,1,0},
            {51,11,13,2017,1,2,0},
            {52,11,14,2017,2,3,0},
            {53,11,15,2017,3,0,3},
            {54,11,16,2017,4,0,4},
            {55,11,20,2017,1,0,5},
            {56,11,21,2017,2,4,0},
            {57,11,28,2017,2,5,0},
            {58,11,29,2017,3,6,0},
            {59,11,30,2017,4,7,0},
            {60,12,1,2017,5,8,0},
            {61,12,4,2017,1,1,0},
            {62,12,5,2017,2,2,0},
            {63,12,6,2017,3,3,0},
            {64,12,7,2017,4,4,0},
            {65,12,8,2017,5,5,0},
            {66,12,11,2017,1,6,0},
            {67,12,12,2017,2,7,0},
            {68,12,13,2017,3,8,0},
            {69,12,14,2017,4,1,0},
            {70,12,15,2017,5,2,0},
            {71,12,18,2017,1,3,0},
            {72,12,19,2017,2,4,0},
            {73,12,20,2017,3,5,0},
            {74,12,21,2017,4,6,0},
            {75,12,22,2017,5,7,6},
            {76,1,8,2018,1,8,0},
            {77,1,9,2018,2,1,0},
            {78,1,10,2018,3,2,0},
            {79,1,11,2018,4,3,0},
            {80,1,12,2018,5,4,0},
            {81,1,16,2018,2,5,0},
            {82,1,17,2018,3,6,0},
            {83,1,18,2018,4,7,0},
            {84,1,19,2018,5,8,0},
            {85,1,22,2018,1,1,0},
            {86,1,23,2018,2,2,0},
            {87,1,24,2018,3,3,0},
            {88,1,25,2018,4,4,0},
            {89,1,26,2018,5,5,0},
            {90,1,29,2018,1,6,0},
            {91,1,30,2018,2,7,0},
            {92,1,31,2018,3,8,0},
            {93,2,1,2018,4,1,0},
            {94,2,2,2018,5,2,0},
            {95,2,5,2018,1,3,0},
            {96,2,6,2018,2,4,0},
            {97,2,7,2018,3,5,0},
            {98,2,8,2018,4,6,0},
            {99,2,9,2018,5,7,0},
            {100,2,12,2018,1,8,0},
            {101,2,13,2018,2,1,0},
            {102,2,14,2018,3,2,0},
            {103,2,15,2018,4,3,0},
            {104,2,16,2018,5,4,0},
            {105,2,20,2018,2,5,0},
            {106,2,21,2018,3,0,3},
            {107,2,22,2018,4,0,4},
            {108,2,26,2018,1,0,5},
            {109,2,27,2018,2,6,0},
            {110,2,28,2018,3,7,0},
            {111,3,1,2018,4,8,0},
            {112,3,2,2018,5,1,0},
            {113,3,5,2018,1,2,0},
            {114,3,6,2018,2,3,0},
            {115,3,7,2018,3,4,0},
            {116,3,8,2018,4,5,0},
            {117,3,9,2018,5,6,0},
            {118,3,12,2018,1,7,0},
            {119,3,13,2018,2,8,0},
            {120,3,14,2018,3,1,0},
            {121,3,15,2018,4,2,0},
            {122,3,16,2018,5,3,0},
            {123,4,3,2018,2,4,0},
            {124,4,4,2018,3,5,0},
            {125,4,5,2018,4,6,0},
            {126,4,6,2018,5,7,0},
            {127,4,9,2018,1,8,0},
            {128,4,10,2018,2,1,0},
            {129,4,11,2018,3,2,0},
            {130,4,12,2018,4,3,0},
            {131,4,13,2018,5,4,0},
            {132,4,16,2018,1,5,0},
            {133,4,17,2018,2,6,0},
            {134,4,18,2018,3,7,0},
            {135,4,19,2018,4,8,0},
            {136,4,20,2018,5,1,0},
            {137,4,23,2018,1,2,0},
            {138,4,24,2018,2,3,0},
            {139,4,25,2018,3,4,0},
            {140,4,26,2018,4,5,0},
            {141,4,27,2018,5,6,0},
            {142,4,30,2018,1,7,0},
            {143,5,1,2018,2,8,0},
            {144,5,2,2018,3,1,0},
            {145,5,3,2018,4,2,0},
            {146,5,4,2018,5,3,0},
            {147,5,7,2018,1,4,0},
            {148,5,8,2018,2,5,0},
            {149,5,9,2018,3,6,0},
            {150,5,10,2018,4,7,0},
            {151,5,11,2018,5,8,0},
            {152,5,14,2018,1,1,0},
            {153,5,15,2018,2,2,0},
            {154,5,16,2018,3,3,0},
            {155,5,17,2018,4,4,0},
            {156,5,18,2018,5,5,0},
            {157,5,21,2018,1,6,0},
            {158,5,22,2018,2,7,0},
            {159,5,23,2018,3,8,0},
            {160,5,24,2018,4,1,0},
            {161,5,25,2018,5,0,7}};

    public int dayCounter = fullSchedule[1][5];
    public String publicDate = "";
    public int fsIndex;
    private String inputString = "";
    public SeekBar seekBar1;
    public static int[][] updatedSchedule;
    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        // Detect a single-click and call my own handler.
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            ListView lv = (ListView)findViewById(R.id.listView);
            int pos = lv.pointToPosition((int)e.getX(), (int)e.getY());
            //myOnItemClick(pos);
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(e1.getY() - e2.getY()) > 100)
                return false;
            if(e1.getX() - e2.getX() >25 &&
                    Math.abs(velocityX) > 5) {
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                realNextDay();
            }  else if (e2.getX() - e1.getX() >25 &&
                    Math.abs(velocityX) >5) {
                realPreviousDay();
            }
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        findToday();







        new refreshSchedule().execute();
        //TextView tv = (TextView) findViewById(R.id.textView);
        //tv.setText("day "+ dayCounter);
       //setupListView();

       // new refreshSchedule().execute();
        /*
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        String strURL = "https://sites.google.com/site/asd324fajke14fakll/f/Untitled.txt?attredirects=0&d=1";
        try {
            int[][] updatedSchedule = new refreshSchedule().readSchedule(strURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        /*try {
            int[][] refreshedSchedule = refreshSchedule.readSchedule(strURL);
        } catch (IOException e) {
            System.out.println("failed to connect to server");
            Toast.makeText(getApplicationContext(),"Failed to Connect to Server", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }*/



    }protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView tv = (TextView) findViewById(R.id.textView);
        publicDate = Integer.toString(fullSchedule[fsIndex][1]) + "/"+Integer.toString(fullSchedule[fsIndex][2]) + "/"+Integer.toString(fullSchedule[fsIndex][3]);
        tv.setText(findWeekDay(fullSchedule[fsIndex][4]) + ", " + publicDate+ "  " + "day "+ dayCounter);
        setupListView();
        seekBar1 = (SeekBar)findViewById(R.id.dayScroller);
        seekBar1.setMax(fullSchedule.length-1);
        seekBar1.setProgress(fsIndex);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fsIndex = progress;
                TextView tv2 = (TextView) findViewById(R.id.textView);
                publicDate = Integer.toString(fullSchedule[fsIndex][1]) + "/"+Integer.toString(fullSchedule[fsIndex][2]) + "/"+Integer.toString(fullSchedule[fsIndex][3]);
                dayCounter = fullSchedule[fsIndex][5];
                tv2.setText(findWeekDay(fullSchedule[fsIndex][4]) + ", " + publicDate+ "  " + "day "+ dayCounter);
                setupListView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        /*
        View view = findViewById(R.id.listView);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
               realNextDay();
            }
            @Override
            public void onSwipeRight() {
                realPreviousDay();
            }
        });
        */

    }

    public String findWeekDay(int i){
        String res = "";
        if(i == 1){res = "Monday";}
        if(i == 2){res = "Tuesday";}
        if(i== 3){res = "Wednesday";}
        if(i == 4){res = "Thursday";}
        if(i == 5){res = "Friday";}

        return res;
    }

   /* public void Share(View view){
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Please Enter Your Name");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
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


    }*/
    public void findToday(){
        System.out.println("Finding today");
        SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy");
        Date now = new Date();
        String compDate = dt.format(now);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if(day == TUESDAY){
            isTuesday = true;
        }else{
            isTuesday= false;
        }
        boolean dateFound = false;
        for(int i = 0; i<fullSchedule.length;i++ ) {
            if ((Integer.parseInt(compDate.substring(0,2)) == fullSchedule[i][1]) && (Integer.parseInt(compDate.substring(3,5)) == fullSchedule[i][2])){
                dayCounter = fullSchedule[i][5];
                publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]);
                fsIndex = i;
                dateFound = true;
                break;
            }
        }
        if(dateFound == false){
            System.out.println("could not find date");
            for(int i = 0; i<fullSchedule.length;i++){
               // System.out.println("compdate = " + compDate);
                if(Integer.parseInt(compDate.substring(6)) == fullSchedule[i][3]){
                    if(Integer.parseInt(compDate.substring(0,2)) == fullSchedule[i][1]){
                        if(Integer.parseInt(compDate.substring(3,5)) < fullSchedule[i][2]){
                            dayCounter = fullSchedule[i][5];
                            publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                            dateFound = true;
                            fsIndex = i;
                            break;
                        }
                    }
                }else if(Integer.parseInt(compDate.substring(6))+1 == fullSchedule[i][3]){
                            dayCounter = fullSchedule[i][5];
                            publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                            dateFound = true;
                            fsIndex = i;
                            break;
                }
                /*if ((Integer.parseInt(compDate.substring(0,2)) == fullSchedule[i][1]) && (Integer.parseInt(compDate.substring(3,5)) < fullSchedule[i][2])){
                    dayCounter = fullSchedule[i][5];
                    publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                    dateFound = true;
                    fsIndex = i;
                    break;
                }

                if ((Integer.parseInt(compDate.substring(0,2)) < fullSchedule[i][1]) ){
                    dayCounter = fullSchedule[i][5];
                    publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                    dateFound = true;
                    fsIndex = i;
                    break;
                }
                else{
                    System.out.println("error setting date 2");
                }*/

            }

        }
    }
    public void todayButtonFunction(View view){
        SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy");
        Date now = new Date();
        String compDate = dt.format(now);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if(day == TUESDAY){
            isTuesday = true;
        }else{
            isTuesday= false;
        }
        boolean dateFound = false;
        for(int i = 0; i<fullSchedule.length;i++ ) {
            if ((Integer.parseInt(compDate.substring(0,2)) == fullSchedule[i][1]) && (Integer.parseInt(compDate.substring(3,5)) == fullSchedule[i][2])){
                dayCounter = fullSchedule[i][5];
                publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]);
                fsIndex = i;
                dateFound = true;
                break;
            }
        }
        if(dateFound == false){
            System.out.println("could not find date");
            for(int i = 0; i<fullSchedule.length;i++){
                // System.out.println("compdate = " + compDate);
                if(Integer.parseInt(compDate.substring(6)) == fullSchedule[i][3]){
                    if(Integer.parseInt(compDate.substring(0,2)) == fullSchedule[i][1]){
                        if(Integer.parseInt(compDate.substring(3,5)) < fullSchedule[i][2]){
                            dayCounter = fullSchedule[i][5];
                            publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                            dateFound = true;
                            fsIndex = i;
                            break;
                        }
                    }
                }else if(Integer.parseInt(compDate.substring(6))+1 == fullSchedule[i][3]){
                    dayCounter = fullSchedule[i][5];
                    publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                    dateFound = true;
                    fsIndex = i;
                    break;
                }
                /*if ((Integer.parseInt(compDate.substring(0,2)) == fullSchedule[i][1]) && (Integer.parseInt(compDate.substring(3,5)) < fullSchedule[i][2])){
                    dayCounter = fullSchedule[i][5];
                    publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                    dateFound = true;
                    fsIndex = i;
                    break;
                }

                if ((Integer.parseInt(compDate.substring(0,2)) < fullSchedule[i][1]) ){
                    dayCounter = fullSchedule[i][5];
                    publicDate = Integer.toString(fullSchedule[i][1]) + "/"+Integer.toString(fullSchedule[i][2]) + "/"+Integer.toString(fullSchedule[i][3]) ;
                    dateFound = true;
                    fsIndex = i;
                    break;
                }
                else{
                    System.out.println("error setting date 2");
                }*/

            }

        }
        TextView tv = (TextView) findViewById(R.id.textView);
        publicDate = Integer.toString(fullSchedule[fsIndex][1]) + "/"+Integer.toString(fullSchedule[fsIndex][2]) + "/"+Integer.toString(fullSchedule[fsIndex][3]);
        tv.setText(findWeekDay(fullSchedule[fsIndex][4]) + ",  " + publicDate+ "  " + "day "+ dayCounter);
        setupListView();
        seekBar1 = (SeekBar)findViewById(R.id.dayScroller);
        seekBar1.setProgress(fsIndex);
    }
    public void editSchedule(View view){
        Intent myIntent = new Intent(getApplicationContext(), addScheduleActivity.class);
        startActivity(myIntent);
    }
    public void nextDay(View view){
        realNextDay();
    }
    public void realNextDay(){
        if(fsIndex< fullSchedule.length-1){
            fsIndex++;
        }
        dayCounter = fullSchedule[fsIndex][5];
        //checkTuesday(fullSchedule[fsIndex][3],fullSchedule[fsIndex][1],fullSchedule[fsIndex][2]);
        if(fullSchedule[fsIndex][4]==2){
            isTuesday = true;
        }else{
            isTuesday = false;
        }
        publicDate = Integer.toString(fullSchedule[fsIndex][1]) + "/"+Integer.toString(fullSchedule[fsIndex][2]) + "/"+Integer.toString(fullSchedule[fsIndex][3]);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(findWeekDay(fullSchedule[fsIndex][4]) + ",  " + publicDate+ "  " + "day "+ dayCounter);
        setupListView();
        seekBar1 = (SeekBar)findViewById(R.id.dayScroller);
        seekBar1.setProgress(fsIndex);
    }
    public void previousDay(View view){
        realPreviousDay();
    }
    public void realPreviousDay(){

        if(fsIndex > 0) {
            fsIndex--;
        }
        dayCounter = fullSchedule[fsIndex][5];
        if(fullSchedule[fsIndex][4]==2){
            isTuesday = true;
        }else{
            isTuesday = false;
        }
        publicDate = Integer.toString(fullSchedule[fsIndex][1]) + "/"+Integer.toString(fullSchedule[fsIndex][2]) + "/"+Integer.toString(fullSchedule[fsIndex][3]);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(findWeekDay(fullSchedule[fsIndex][4]) + ",  " + publicDate+ "  " + "day "+ dayCounter);
        setupListView();
        seekBar1 = (SeekBar)findViewById(R.id.dayScroller);
        seekBar1.setProgress(fsIndex);
    }

    public void viewSchedule(View view){
        //setContentView(R.layout.activity_main);
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
       // finish();
        startActivity(myIntent);
    }
    public void viewBarcode(View view){

        Intent myIntent = new Intent(getApplicationContext(), barcodeActivity.class);
        //finish();
        startActivity(myIntent);
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

    public void setupListView() {

        final String[] scheduleNames = {"day0Schedule", "day1Schedule", "day2Schedule", "day3Schedule", "day4Schedule", "day5Schedule", "day6Schedule", "day7Schedule", "day8Schedule"};
        String[] notTuesdayTimes = {"Period 1: 8:15-9:05  ", "Period 2: 9:10-10:00 ", "Period 3: 10:35-11:45", "Period 4a: 11:50-12:20", "Period 4b: 12:25-12:40", "Period 4c: 12:45-1:15 ", "Period 5: 1:20-2:10  ", "Period 6: 2:15-3:00  "};
        String[] tuesdayTimes = {"Period 1: 8:50-9:40", "Period 2: 9:45-10:35", "Period 3: 10:40-11:50", "Period 4a: 11:55-12:25", "Period 4b: 12:30-12:45", "Period 4c: 12:50-1:20", "Period 5: 1:25-2:15", "Period 6: 2:15-3:05"};
        String[] dayZeroTimes = {"Day 1: 8:15-8:50", "Day 2: 8:55-9:30", "Day 3: 9:35-10:10", "Day 4: 10:15-10:50", "Day 5: 11:35-12:10", "Day 6: 12:15-12:50", "Day 7: 12:55-1:30", "Day 8: 1:35-2:10"};
        final ArrayList<String> courseNames = new ArrayList<>();
        final ArrayList<String> editNames = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput(scheduleNames[dayCounter])));
            int lineCounter = 0;
            while (true) {

                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                editNames.add(line);
                if (!isTuesday) {
                    if (lineCounter == 2) {
                        courseNames.add("10:05-10:30" + "                     " + "Assembly");
                    }
                    courseNames.add(notTuesdayTimes[lineCounter] + "     " + line);
                    lineCounter++;
                } else {
                    courseNames.add(tuesdayTimes[lineCounter] + "     " + line);
                    lineCounter++;
                }

            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
        ListView listDays = (ListView) findViewById(R.id.listView);
        final ListView listDays2 = (ListView) findViewById(R.id.listView); //seems wrong
        if (isTuesday) {
            courseNames.add("");
        }
        if (dayCounter == 0) {
            courseNames.clear();
            int lineCounter = 0;
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[1])));

                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[2])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[3])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[4])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            courseNames.add("10:55-11:35           BREAK");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[5])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[6])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[7])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput(scheduleNames[8])));
                String line = reader.readLine();
                courseNames.add(dayZeroTimes[lineCounter] + "     " + line);
                lineCounter++;
            } catch (IOException e) {
                System.out.println("error reading or writing to file");
                e.printStackTrace();
            }

        }
        ArrayList<String> myFriends = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("friendFile")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                myFriends.add(line);
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to friend file");
            e.printStackTrace();
        }
        String[] courseNamesArray = new String[courseNames.size()];
        for (int x = 0; x < courseNames.size(); x++) {
            courseNamesArray[x] = courseNames.get(x);
        }
        for (int x = 0; x < courseNames.size(); x++) {
            if ((dayCounter != 0) && (courseNames.get(x).contains("Free") || courseNames.get(x).contains("Lunch"))) {
                courseNamesArray[x]+='\n';
                //courseNamesArray[x]+="                                        ";
                System.out.println("starting the friend process");
                for (int i = 0; i < myFriends.size(); i++) {
                    String[] myFriendsInfo = myFriends.get(i).split(",");
                    for (int j = 1; j < myFriendsInfo.length; j++) {
                        if (Integer.parseInt(myFriendsInfo[j]) == pairIndex(dayCounter, x)) {
                            courseNamesArray[x] += /*courseNames.get(x)+ */" " + makeInitial(myFriendsInfo[0]);
                            System.out.println(courseNamesArray[x]);
                        }
                    }
                }
            }
        }
        courseNames.clear();
        for (int i = 0; i < courseNamesArray.length; i++) {
            courseNames.add(courseNamesArray[i]);
        }
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNames);
        listDays.setAdapter(arrayAdapter);

      /*  listDays.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            // argument position gives the index of item which is clicked
            public boolean onItemLongClick(AdapterView<?> arg0, View v, final int position, long arg3) {
                if (dayCounter != 0) {
                    Toast.makeText(getApplicationContext(), courseNames.get(position) + " Selected", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("What would you like to rename this class to?");
                    final EditText input = new EditText(MainActivity.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);
                    builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inputString = input.getText().toString();
                            try {
                                OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                                        scheduleNames[dayCounter], Context.MODE_PRIVATE));
                                for(int i = 0; i<editNames.size();i++) {
                                    int testPos;
                                    if(!isTuesday) {
                                        if (position >= 2) {
                                            testPos = position - 1;
                                        } else {
                                            testPos = position;
                                        }
                                        if (i == testPos) {
                                            writer.write(inputString + '\n');
                                        } else {
                                            writer.write(editNames.get(i) + '\n');
                                        }
                                    }
                                    if(isTuesday) {

                                            testPos = position;

                                        if (i == testPos) {
                                            writer.write(inputString + '\n');
                                        } else {
                                            writer.write(editNames.get(i) + '\n');
                                        }
                                    }
                                }
                               // System.out.println("writing username" + inputString);
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            setupListView();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();

                }

                    System.out.println("long clicking");
                    return true;
                }
        });*/
        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int REL_SWIPE_MIN_DISTANCE = (int) (120.0f * dm.densityDpi / 160.0f + 0.5);
        final int REL_SWIPE_MAX_OFF_PATH = (int) (250.0f * dm.densityDpi / 160.0f + 0.5);
        final int REL_SWIPE_THRESHOLD_VELOCITY = (int) (200.0f * dm.densityDpi / 160.0f + 0.5);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                courseNames));
        final GestureDetector gestureDetector = new GestureDetector(new MyGestureDetector());
        View.OnTouchListener gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        lv.setOnTouchListener(gestureListener);

        // Long-click still works in the usual way.
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (dayCounter != 0) {
                    Toast.makeText(getApplicationContext(), courseNames.get(position) + " Selected", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("What would you like to rename this class to?");
                    final EditText input = new EditText(MainActivity.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);
                    builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            inputString = input.getText().toString();
                            try {
                                OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                                        scheduleNames[dayCounter], Context.MODE_PRIVATE));
                                for(int i = 0; i<editNames.size();i++) {
                                    int testPos;
                                    if(!isTuesday) {
                                        if (position >= 2) {
                                            testPos = position - 1;
                                        } else {
                                            testPos = position;
                                        }
                                        if (i == testPos) {
                                            writer.write(inputString + '\n');
                                        } else {
                                            writer.write(editNames.get(i) + '\n');
                                        }
                                    }
                                    if(isTuesday) {

                                        testPos = position;

                                        if (i == testPos) {
                                            writer.write(inputString + '\n');
                                        } else {
                                            writer.write(editNames.get(i) + '\n');
                                        }
                                    }
                                }
                                // System.out.println("writing username" + inputString);
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            setupListView();

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();

                }

                System.out.println("long clicking");
                return true;
            }

        });


        /*listDays2.setOnTouchListener(new OnSwipeTouchListener(this) {


            @Override
            public void onSwipeLeft() {
                realNextDay();
            }
            @Override
            public void onSwipeRight() {
                realPreviousDay();
            }

        });*/

        //}

    }
    public void onBackPressed(){
        back(null);
        TextView tv = (TextView) findViewById(R.id.textView);
        publicDate = Integer.toString(fullSchedule[fsIndex][1]) + "/"+Integer.toString(fullSchedule[fsIndex][2]) + "/"+Integer.toString(fullSchedule[fsIndex][3]);
        tv.setText(findWeekDay(fullSchedule[fsIndex][4]) + ",  " + publicDate+ "  " + "day "+ dayCounter);
        seekBar1.setProgress(fsIndex);
    }


    public void back(View view){
        setContentView(R.layout.activity_main);
        setupListView();
    }

    public void findSharedFrees(){
        ArrayList<String> myFriends = new ArrayList<>();
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
            for(int j = 0; j<myFriendsInfo.length;j++){
                if(myFriendsInfo[i].equals("Free")){

                }
            }
        }

    }
    public String makeInitial(String fullName){
        String initials = "";
        for(int i = 0; i < fullName.length();i++){
            if(Character.isUpperCase(fullName.charAt(i))){
                initials += fullName.charAt(i);
            }
        }
        return initials;
    }
    public void findMyFrees(){
        myFrees.clear();
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
            if(fullScheduleArrayList.get(i).equals("Free")){
               myFrees.add(fullScheduleArrayList.get(i));
            }
        }

    }
    public int pairIndex(int fileIndex, int toPair){
        int pairedResult = 0;
        if(!isTuesday && toPair >=2){
            toPair--;
        }
        if(fileIndex ==1){
            pairedResult = toPair;
        }
        else{
            pairedResult = (8*(fileIndex-1)) + toPair;
        }
        return pairedResult;
    }




/*/server refresh stuff

    public static int[][] readSchedule(String strURL) throws IOException{
        URL url = new URL(strURL);
        URLConnection urlConnection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        ArrayList<String> strInfo = new ArrayList<String>();
        try {
            String line = br.readLine();
            while (line != null) {
                strInfo.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
        finally {
            br.close();
        }
        int[][]info = new int[strInfo.size()][7];
        for (int i=0;i<strInfo.size();i++){
            info[i] = extractArray(strInfo.get(i));
        }
        return info;
    }
    public static int[] extractArray(String line){
        String[] parts = line.split(",");

        int[] values = new int[7];
        int i = 0;
        for (String piece: parts){
            String number = "";
            for (char c:piece.toCharArray()){
                if (isNumeric(c+"")){
                    number += c;
                }
            }
            if (number.length() > 0){
                values[i] = Integer.parseInt(number);
            }
            i++;
        }
        return values;
    }
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
*/

}