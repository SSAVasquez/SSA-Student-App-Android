package com.fuady.ssa_app_redo1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by fuady on 7/5/2017.
 */

public class editDayScheduleActivity extends AppCompatActivity{
    public EditText activeEdit1;
    public EditText activeEdit2;
    public EditText activeEdit3;
    public EditText activeEdit4;
    public EditText activeEdit5;
    public EditText activeEdit6;
    public EditText activeEdit7;
    public EditText activeEdit8;

    public String useName = "";
    public String fileName = "";
    public ArrayList<EditText> myEditTexts = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_day_schedule_screen);
        useName = getIntent().getStringExtra(editScheduleActivity.ID_EXTRA_NAME);
        TextView tv = (TextView)findViewById(R.id.infoTab);
        tv.setText(useName);
        if(useName.equals("Edit Day 1 Schedule")){fileName = "day1Schedule";}
        if(useName.equals("Edit Day 2 Schedule")){fileName = "day2Schedule";}
        if(useName.equals("Edit Day 3 Schedule")){fileName = "day3Schedule";}
        if(useName.equals("Edit Day 4 Schedule")){fileName = "day4Schedule";}
        if(useName.equals("Edit Day 5 Schedule")){fileName = "day5Schedule";}
        if(useName.equals("Edit Day 6 Schedule")){fileName = "day6Schedule";}
        if(useName.equals("Edit Day 7 Schedule")){fileName = "day7Schedule";}
        if(useName.equals("Edit Day 8 Schedule")){fileName = "day8Schedule";}
        activeEdit1 = (EditText) findViewById(R.id.e1);
        activeEdit2 = (EditText) findViewById(R.id.e2);
        activeEdit3 = (EditText) findViewById(R.id.e3);
        activeEdit4 = (EditText) findViewById(R.id.e4);
        activeEdit5 = (EditText) findViewById(R.id.e5);
        activeEdit6 = (EditText) findViewById(R.id.e6);
        activeEdit7 = (EditText) findViewById(R.id.e7);
        activeEdit8 = (EditText) findViewById(R.id.e8);
        myEditTexts.add(activeEdit1);
        myEditTexts.add(activeEdit2);
        myEditTexts.add(activeEdit3);
        myEditTexts.add(activeEdit4);
        myEditTexts.add(activeEdit5);
        myEditTexts.add(activeEdit6);
        myEditTexts.add(activeEdit7);
        myEditTexts.add(activeEdit8);
        setupEditTexts();

    }



    public void enterSchedule(View view){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    fileName, Context.MODE_PRIVATE));
            for(int i = 0; i <myEditTexts.size(); i++) {
                System.out.println(myEditTexts.get(i).toString());
                writer.write(myEditTexts.get(i).getText().toString() + '\n');
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setupEditTexts(){

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput(fileName)));
            int i = 0;
            while (true && i < myEditTexts.size()) {

                String line = reader.readLine();
                String[] result = new String[3];
                // System.out.println("line = "+ line);
                if (line != null) {
                    result = line.split(" ");
                }
                if (line == null) {
                    break;
                }

                myEditTexts.get(i).setText(result[0]);
                i++;

            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
    }




}



