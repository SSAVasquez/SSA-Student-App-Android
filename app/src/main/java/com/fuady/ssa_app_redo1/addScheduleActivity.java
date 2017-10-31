package com.fuady.ssa_app_redo1;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by fuady on 7/5/2017.
 */


class Course {
    String name;
    Boolean is4ab;
    Boolean isScience;
    ArrayList<String> friendsNames;

    public Course(String name, Boolean is4ab, Boolean isScience) {
        this.name = name;
        this.is4ab = is4ab;
        this.isScience = isScience;
    }
}

public class addScheduleActivity extends AppCompatActivity {
    static Course filler = new Course("", false, false);
    // static Course[] myCourses = {filler,filler,filler,filler,filler,filler,filler,filler};
    //new Course[8];
    // {filler,filler,filler,filler,filler,filler,filler,filler};
    public ArrayList<Course> myCourses = new ArrayList<>();
   // public ArrayList<Course> fullSchedule = new ArrayList<>();
    public Course day1 = new Course("", false,false);
    public Course day2 = new Course("", false, false);
    public Course day3 = new Course("", false, false);
    public Course day4 = new Course("", false, false);
    public Course day5 = new Course("", false, false);
    public Course day6 = new Course("", false, false);
    public Course day7 = new Course("", false, false);
    public Course day8 = new Course("", false, false);

    public EditText activeEdit1;
    public EditText activeEdit2;
    public EditText activeEdit3;
    public EditText activeEdit4;
    public EditText activeEdit5;
    public EditText activeEdit6;
    public EditText activeEdit7;
    public EditText activeEdit8;

    public CheckBox ch1;
    public CheckBox ch2;
    public CheckBox ch3;
    public CheckBox ch4;
    public CheckBox ch5;
    public CheckBox ch6;
    public CheckBox ch7;
    public CheckBox ch8;

    public CheckBox cb1;
    public CheckBox cb2;
    public CheckBox cb3;
    public CheckBox cb4;
    public CheckBox cb5;
    public CheckBox cb6;
    public CheckBox cb7;
    public CheckBox cb8;








    public ArrayList<EditText> myEditTexts = new ArrayList<EditText>();
    public ArrayList<CheckBox> myCBs = new ArrayList<CheckBox>();
    public ArrayList<CheckBox> mySCBs = new ArrayList<CheckBox>();
    // public EditText[] myEditTexts = new EditText[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule_screen);
        try {
            OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "period4Courses", Context.MODE_PRIVATE));
            writerTwo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //moved from onResume to prevent crashing on returning from recent apps button
        activeEdit1 = (EditText) findViewById(R.id.day1et);
        activeEdit2 = (EditText) findViewById(R.id.day2et);
        activeEdit3 = (EditText) findViewById(R.id.day3et);
        activeEdit4 = (EditText) findViewById(R.id.day4et);
        activeEdit5 = (EditText) findViewById(R.id.day5et);
        activeEdit6 = (EditText) findViewById(R.id.day6et);
        activeEdit7 = (EditText) findViewById(R.id.day7et);
        activeEdit8 = (EditText) findViewById(R.id.day8et);
        ch1 = (CheckBox) findViewById(R.id.checkBox);
        ch2 = (CheckBox) findViewById(R.id.checkBox2);
        ch3 = (CheckBox) findViewById(R.id.checkBox3);
        ch4 = (CheckBox) findViewById(R.id.checkBox4);
        ch5 = (CheckBox) findViewById(R.id.checkBox5);
        ch6 = (CheckBox) findViewById(R.id.checkBox6);
        ch7 = (CheckBox) findViewById(R.id.checkBox7);
        ch8 = (CheckBox) findViewById(R.id.checkBox8);


        cb1 = (CheckBox) findViewById(R.id.checkBox9);
        cb2 = (CheckBox) findViewById(R.id.checkBox10);
        cb3 = (CheckBox) findViewById(R.id.checkBox11);
        cb4 = (CheckBox) findViewById(R.id.checkBox12);
        cb5 = (CheckBox) findViewById(R.id.checkBox13);
        cb6 = (CheckBox) findViewById(R.id.checkBox14);
        cb7 = (CheckBox) findViewById(R.id.checkBox15);
        cb8 = (CheckBox) findViewById(R.id.checkBox16);


        myCBs.add(ch1);
        myCBs.add(ch2);
        myCBs.add(ch3);
        myCBs.add(ch4);
        myCBs.add(ch5);
        myCBs.add(ch6);
        myCBs.add(ch7);
        myCBs.add(ch8);

        mySCBs.add(cb1);
        mySCBs.add(cb2);
        mySCBs.add(cb3);
        mySCBs.add(cb4);
        mySCBs.add(cb5);
        mySCBs.add(cb6);
        mySCBs.add(cb7);
        mySCBs.add(cb8);



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
    protected void onResume() {
        super.onResume();

    }
    public void setupEditTexts(){
        myCourses.clear();
        myCourses.add(day1);
        myCourses.add(day2);
        myCourses.add(day3);
        myCourses.add(day4);
        myCourses.add(day5);
        myCourses.add(day6);
        myCourses.add(day7);
        myCourses.add(day8);

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("period4Courses")));
            int i = 0;
            while (true) {

                String[] result = new String[3];
                String line = reader.readLine();
                // System.out.println("line = "+ line);
                if (line != null) {
                    result = line.split(",");
                }
                if (line == null) {
                    break;
                }

                myCourses.get(i).name = result[0];
                myEditTexts.get(i).setText(myCourses.get(i).name);
                if (result[1].equals("0")) {
                    // Course newCourse = new Course(result[0],true);
                    // myCourses[i]= newCourse;
                    myCourses.get(i).is4ab = true;
                    myCBs.get(i).setChecked(true);
                    System.out.println(myCourses.get(i).name + " 0");
                } else {
                    // Course newCourse = new Course(result[0],false);
                    //  myCourses[i]= newCourse;
                    myCourses.get(i).is4ab = false;
                    myCBs.get(i).setChecked(false);
                    System.out.println(myCourses.get(i).name + " 1");
                }
                if(result[2].equals("0")){
                    myCourses.get(i).isScience = true;
                    mySCBs.get(i).setChecked(true);
                }else{
                    myCourses.get(i).isScience = false;
                    mySCBs.get(i).setChecked(false);
                }
                System.out.println("mmmmyCourses of" + i + " = " + myCourses.get(i).name);
                i++;

            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
        for(int i = 0; i < myEditTexts.size(); i++){
            myEditTexts.get(i).setText(myCourses.get(i).name);
        }
    }

    public void onCheckBoxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();
        switch(view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day1.is4ab =true;
                    System.out.println("course 1 is true");
                } else {
                    day1.is4ab=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    //myCourses[1].is4ab = true;
                    day2.is4ab =true;
                    System.out.println("course 2 is true");
                } else {
                    day2.is4ab=false;
                    // myCourses[1].is4ab = false;
                    System.out.println("course 2 is false");
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    day3.is4ab=true;
                    //myCourses[2].is4ab = true;
                    System.out.println("course 3 is true");
                } else {
                    day3.is4ab=false;
                    //myCourses[2].is4ab = false;
                    System.out.println("course 3 is false");
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    day4.is4ab=true;
                    // myCourses[3].is4ab = true;
                    System.out.println("course 4 is true");
                } else {
                    day4.is4ab=false;
                    //myCourses[3].is4ab = false;
                    System.out.println("course 4 is false");
                }
                break;
            case R.id.checkBox5:
                if (checked) {
                    day5.is4ab=true;
                    //myCourses[4].is4ab = true;
                    System.out.println("course 3 is true");
                } else {
                    day5.is4ab=false;
                    //myCourses[4].is4ab = false;
                    System.out.println("course 3 is false");
                }
                break;
            case R.id.checkBox6:
                if (checked) {
                    day6.is4ab=true;
                    //myCourses[5].is4ab = true;
                    System.out.println("course 3 is true");
                } else {
                    day6.is4ab=false;
                    // myCourses[5].is4ab = false;
                    System.out.println("course 3 is false");
                }
                break;
            case R.id.checkBox7:
                if (checked) {
                    day7.is4ab=true;
                    //myCourses[6].is4ab = true;
                    System.out.println("course 3 is true");
                } else {
                    day7.is4ab=false;
                    // myCourses[6].is4ab = false;
                    System.out.println("course 3 is false");
                }
                break;
            case R.id.checkBox8:
                if (checked) {
                    day8.is4ab=true;
                    // myCourses[7].is4ab = true;
                    System.out.println("course 3 is true");
                } else {
                    day8.is4ab=false;
                    //myCourses[7].is4ab = false;
                    System.out.println("course 3 is false");
                }
                break;
            case R.id.checkBox9:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day1.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day1.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox10:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day2.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day2.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox11:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day3.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day3.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox12:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day4.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day4.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox13:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day5.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day5.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox14:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day6.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day6.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox15:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day7.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day7.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
            case R.id.checkBox16:
                if (checked) {
                    //myCourses[0].is4ab = true;
                    day8.isScience =true;
                    System.out.println("course 1 is true");
                } else {
                    day8.isScience=false;
                    // myCourses[0].is4ab = false;
                    System.out.println("course 1 is false");
                }
                break;
        }
    }
    public void addSchedule(View view){
        myCourses.clear();
        if(activeEdit1.getText().toString() == "Empty" || activeEdit1.getText().toString() == "" || activeEdit1.getText().toString().contains("free")) {
            day1.name = "Free";
        }else{
            day1.name = activeEdit1.getText().toString();
        }
        if(activeEdit2.getText().toString() == "Empty"|| activeEdit2.getText().toString() == "" || activeEdit2.getText().toString().contains("free")) {
            day2.name = "Free";
        }else{
            day2.name = activeEdit2.getText().toString();
        }
        if(activeEdit3.getText().toString() == "Empty"|| activeEdit3.getText().toString() == "" || activeEdit3.getText().toString().contains("free")) {
            day3.name = "Free";
        }else{
            day3.name = activeEdit3.getText().toString();
        }
        if(activeEdit4.getText().toString() == "Empty"|| activeEdit4.getText().toString() == "" || activeEdit4.getText().toString().contains("free")) {
            day4.name = "Free";
        }else{
            day4.name = activeEdit4.getText().toString();
        }
        if(activeEdit5.getText().toString() == "Empty"|| activeEdit5.getText().toString() == "" || activeEdit5.getText().toString().contains("free")) {
            day5.name = "Free";
        }else{
            day5.name = activeEdit5.getText().toString();
        }
        if(activeEdit6.getText().toString() == "Empty"|| activeEdit6.getText().toString() == "" || activeEdit6.getText().toString().contains("free")) {
            day6.name = "Free";
        }else{
            day6.name = activeEdit6.getText().toString();
        }
        if(activeEdit7.getText().toString() == "Empty"|| activeEdit7.getText().toString() == "" || activeEdit7.getText().toString().contains("free")) {
            day7.name = "Free";
        }else{
            day7.name = activeEdit7.getText().toString();
        }
        if(activeEdit8.getText().toString() == "Empty"|| activeEdit8.getText().toString() == "" || activeEdit8.getText().toString().contains("free")) {
            day8.name = "Free";
        }else{
            day8.name = activeEdit8.getText().toString();
        }
        myCourses.add(day1);
        myCourses.add(day2);
        myCourses.add(day3);
        myCourses.add(day4);
        myCourses.add(day5);
        myCourses.add(day6);
        myCourses.add(day7);
        myCourses.add(day8);
        try {
            OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "period4Courses", Context.MODE_PRIVATE));
            writerTwo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "period4Courses", Context.MODE_APPEND | Context.MODE_PRIVATE));
            for(int i = 0; i<myCourses.size(); i++){
                String toWrite = myCourses.get(i).name;
                if(myCourses.get(i).is4ab == true){
                    toWrite += ",0";
                }
                if(myCourses.get(i).is4ab == false){
                    toWrite += ",1";
                }
                if(myCourses.get(i).isScience == true){
                    toWrite += ",0";
                }
                if(myCourses.get(i).isScience == false){
                    toWrite += ",1";
                }
                writer.write(toWrite + '\n');
                /*
                if(myCourses.get(i).is4ab == true ){
                    writer.write(myCourses.get(i).name + " " + '0' + '\n');
                    System.out.println("adding " + myEditTexts.get(i).getText().toString() + " 0 to sch " );
                }else{
                    writer.write(myEditTexts.get(i).getText().toString() + " " + '1' + '\n');
                    System.out.println("adding " + myEditTexts.get(i).getText().toString() + " 1 to sch " );
                }*/
                // System.out.println(myCourses[i].name);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day1Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(4).name + '\n');
            if(myCourses.get(4).isScience == true){
                writer.write(myCourses.get(4).name + '\n');
            }else {
                writer.write(myCourses.get(7).name + '\n');
            }
            writer.write(myCourses.get(2).name + '\n');
            if(myCourses.get(0).is4ab==true){
                writer.write(myCourses.get(0).name + '\n');
                writer.write(myCourses.get(0).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(0).name + '\n');
                writer.write(myCourses.get(0).name + '\n');
            }
            if(myCourses.get(0).is4ab == false && myCourses.get(0).isScience == true){
                writer.write(myCourses.get(0).name + '\n');
            }else {
                writer.write(myCourses.get(3).name + '\n');
            }
            writer.write(myCourses.get(6).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day2Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(5).name + '\n');
            if(myCourses.get(5).isScience == true){
                writer.write(myCourses.get(5).name + '\n');
            }else {
                writer.write(myCourses.get(0).name + '\n');
            }
            writer.write(myCourses.get(3).name + '\n');
            if(myCourses.get(1).is4ab==true){
                writer.write(myCourses.get(1).name + '\n');
                writer.write(myCourses.get(1).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(1).name + '\n');
                writer.write(myCourses.get(1).name + '\n');
            }
            if(myCourses.get(1).is4ab == false && myCourses.get(1).isScience == true){
                writer.write(myCourses.get(1).name + '\n');
            }else {
                writer.write(myCourses.get(4).name + '\n');
            }
            writer.write(myCourses.get(7).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day3Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(6).name + '\n');
            if(myCourses.get(6).isScience == true){
                writer.write(myCourses.get(6).name + '\n');
            }else {
                writer.write(myCourses.get(1).name + '\n');
            }
            writer.write(myCourses.get(4).name + '\n');
            if(myCourses.get(2).is4ab==true){
                writer.write(myCourses.get(2).name + '\n');
                writer.write(myCourses.get(2).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(2).name + '\n');
                writer.write(myCourses.get(2).name + '\n');
            }
            if(myCourses.get(2).is4ab == false && myCourses.get(2).isScience == true){
                writer.write(myCourses.get(2).name + '\n');
            }else {
                writer.write(myCourses.get(5).name + '\n');
            }
            writer.write(myCourses.get(0).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day4Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(7).name + '\n');
            if(myCourses.get(7).isScience == true){
                writer.write(myCourses.get(7).name + '\n');
            }else {
                writer.write(myCourses.get(2).name + '\n');
            }
            writer.write(myCourses.get(5).name + '\n');
            if(myCourses.get(3).is4ab==true){
                writer.write(myCourses.get(3).name + '\n');
                writer.write(myCourses.get(3).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(3).name + '\n');
                writer.write(myCourses.get(3).name + '\n');
            }
            if(myCourses.get(3).is4ab == false && myCourses.get(3).isScience == true){
                writer.write(myCourses.get(3).name + '\n');
            }else {
                writer.write(myCourses.get(6).name + '\n'); //not sure about this one
            }
            writer.write(myCourses.get(1).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day5Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(0).name + '\n');
            if(myCourses.get(0).isScience == true){
                writer.write(myCourses.get(0).name + '\n');
            }else {
                writer.write(myCourses.get(3).name + '\n');
            }
            writer.write(myCourses.get(6).name + '\n');
            if(myCourses.get(4).is4ab==true){
                writer.write(myCourses.get(4).name + '\n');
                writer.write(myCourses.get(4).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(4).name + '\n');
                writer.write(myCourses.get(4).name + '\n');
            }
            if(myCourses.get(4).is4ab == false && myCourses.get(4).isScience == true){
                writer.write(myCourses.get(4).name + '\n');
            }else {
                writer.write(myCourses.get(7).name + '\n');
            }
            writer.write(myCourses.get(2).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day6Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(1).name + '\n');
            if(myCourses.get(1).isScience == true){
                writer.write(myCourses.get(1).name + '\n');
            }else {
                writer.write(myCourses.get(4).name + '\n');
            }
            writer.write(myCourses.get(7).name + '\n');
            if(myCourses.get(5).is4ab==true){
                writer.write(myCourses.get(5).name + '\n');
                writer.write(myCourses.get(5).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(5).name + '\n');
                writer.write(myCourses.get(5).name + '\n');
            }
            if(myCourses.get(5).is4ab == false && myCourses.get(5).isScience == true){
                writer.write(myCourses.get(5).name + '\n');
            }else {
                writer.write(myCourses.get(0).name + '\n');
            }
            writer.write(myCourses.get(3).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day7Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(2).name + '\n');
            if(myCourses.get(2).isScience == true){
                writer.write(myCourses.get(2).name + '\n');
            }else {
                writer.write(myCourses.get(5).name + '\n');
            }
            writer.write(myCourses.get(0).name + '\n');
            if(myCourses.get(6).is4ab==true){
                writer.write(myCourses.get(6).name + '\n');
                writer.write(myCourses.get(6).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(6).name + '\n');
                writer.write(myCourses.get(6).name + '\n');
            }
            if(myCourses.get(6).is4ab == false && myCourses.get(6).isScience == true){
                writer.write(myCourses.get(6).name + '\n');
            }else {
                writer.write(myCourses.get(1).name + '\n');
            }
            writer.write(myCourses.get(4).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "day8Schedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(3).name + '\n');
            if(myCourses.get(3).isScience == true){
                writer.write(myCourses.get(3).name + '\n');
            }else {
                writer.write(myCourses.get(6).name + '\n'); //not sure
            }
            writer.write(myCourses.get(1).name + '\n');
            if(myCourses.get(7).is4ab==true){
                writer.write(myCourses.get(7).name + '\n');
                writer.write(myCourses.get(7).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(7).name + '\n');
                writer.write(myCourses.get(7).name + '\n');
            }
            if(myCourses.get(7).is4ab == false && myCourses.get(7).isScience == true){
                writer.write(myCourses.get(7).name + '\n');
            }else {
                writer.write(myCourses.get(2).name + '\n');
            }
            writer.write(myCourses.get(5).name + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }







        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "allDaysSchedule", Context.MODE_PRIVATE));
            writer.write(myCourses.get(4).name + '\n');
            if(myCourses.get(4).isScience == true){
                writer.write(myCourses.get(4).name + '\n');
            }else {
                writer.write(myCourses.get(7).name + '\n');
            }
            writer.write(myCourses.get(2).name + '\n');
            if(myCourses.get(0).is4ab==true){
                writer.write(myCourses.get(0).name + '\n');
                writer.write(myCourses.get(0).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(0).name + '\n');
                writer.write(myCourses.get(0).name + '\n');
            }
            if(myCourses.get(0).is4ab == false && myCourses.get(0).isScience == true){
                writer.write(myCourses.get(0).name + '\n');
            }else {
                writer.write(myCourses.get(3).name + '\n');
            }
            writer.write(myCourses.get(6).name + '\n');

            writer.write(myCourses.get(5).name + '\n');
            if(myCourses.get(5).isScience == true){
                writer.write(myCourses.get(5).name + '\n');
            }else {
                writer.write(myCourses.get(0).name + '\n');
            }
            writer.write(myCourses.get(3).name + '\n');
            if(myCourses.get(1).is4ab==true){
                writer.write(myCourses.get(1).name + '\n');
                writer.write(myCourses.get(1).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(1).name + '\n');
                writer.write(myCourses.get(1).name + '\n');
            }
            if(myCourses.get(1).is4ab == false && myCourses.get(1).isScience == true){
                writer.write(myCourses.get(1).name + '\n');
            }else {
                writer.write(myCourses.get(4).name + '\n');
            }
            writer.write(myCourses.get(7).name + '\n');

            writer.write(myCourses.get(6).name + '\n');
            if(myCourses.get(6).isScience == true){
                writer.write(myCourses.get(6).name + '\n');
            }else {
                writer.write(myCourses.get(1).name + '\n');
            }
            writer.write(myCourses.get(4).name + '\n');
            if(myCourses.get(2).is4ab==true){
                writer.write(myCourses.get(2).name + '\n');
                writer.write(myCourses.get(2).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(2).name + '\n');
                writer.write(myCourses.get(2).name + '\n');
            }
            if(myCourses.get(2).is4ab == false && myCourses.get(2).isScience == true){
                writer.write(myCourses.get(2).name + '\n');
            }else {
                writer.write(myCourses.get(5).name + '\n');
            }
            writer.write(myCourses.get(0).name + '\n');

            writer.write(myCourses.get(7).name + '\n');
            if(myCourses.get(7).isScience == true){
                writer.write(myCourses.get(7).name + '\n');
            }else {
                writer.write(myCourses.get(2).name + '\n');
            }
            writer.write(myCourses.get(5).name + '\n');
            if(myCourses.get(3).is4ab==true){
                writer.write(myCourses.get(3).name + '\n');
                writer.write(myCourses.get(3).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(3).name + '\n');
                writer.write(myCourses.get(3).name + '\n');
            }
            if(myCourses.get(3).is4ab == false && myCourses.get(3).isScience == true){
                writer.write(myCourses.get(3).name + '\n');
            }else {
                writer.write(myCourses.get(6).name + '\n'); //not sure about this one
            }
            writer.write(myCourses.get(1).name + '\n');

            writer.write(myCourses.get(0).name + '\n');
            if(myCourses.get(0).isScience == true){
                writer.write(myCourses.get(0).name + '\n');
            }else {
                writer.write(myCourses.get(3).name + '\n');
            }
            writer.write(myCourses.get(6).name + '\n');
            if(myCourses.get(4).is4ab==true){
                writer.write(myCourses.get(4).name + '\n');
                writer.write(myCourses.get(4).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(4).name + '\n');
                writer.write(myCourses.get(4).name + '\n');
            }
            if(myCourses.get(4).is4ab == false && myCourses.get(4).isScience == true){
                writer.write(myCourses.get(4).name + '\n');
            }else {
                writer.write(myCourses.get(7).name + '\n');
            }
            writer.write(myCourses.get(2).name + '\n');

            writer.write(myCourses.get(1).name + '\n');
            if(myCourses.get(1).isScience == true){
                writer.write(myCourses.get(1).name + '\n');
            }else {
                writer.write(myCourses.get(4).name + '\n');
            }
            writer.write(myCourses.get(7).name + '\n');
            if(myCourses.get(5).is4ab==true){
                writer.write(myCourses.get(5).name + '\n');
                writer.write(myCourses.get(5).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(5).name + '\n');
                writer.write(myCourses.get(5).name + '\n');
            }
            if(myCourses.get(5).is4ab == false && myCourses.get(5).isScience == true){
                writer.write(myCourses.get(5).name + '\n');
            }else {
                writer.write(myCourses.get(0).name + '\n');
            }
            writer.write(myCourses.get(3).name + '\n');

            writer.write(myCourses.get(2).name + '\n');
            if(myCourses.get(2).isScience == true){
                writer.write(myCourses.get(2).name + '\n');
            }else {
                writer.write(myCourses.get(5).name + '\n');
            }
            writer.write(myCourses.get(0).name + '\n');
            if(myCourses.get(6).is4ab==true){
                writer.write(myCourses.get(6).name + '\n');
                writer.write(myCourses.get(6).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(6).name + '\n');
                writer.write(myCourses.get(6).name + '\n');
            }
            if(myCourses.get(6).is4ab == false && myCourses.get(6).isScience == true){
                writer.write(myCourses.get(6).name + '\n');
            }else {
                writer.write(myCourses.get(1).name + '\n');
            }
            writer.write(myCourses.get(4).name + '\n');

            writer.write(myCourses.get(3).name + '\n');
            if(myCourses.get(3).isScience == true){
                writer.write(myCourses.get(3).name + '\n');
            }else {
                writer.write(myCourses.get(6).name + '\n'); //not sure
            }
            writer.write(myCourses.get(1).name + '\n');
            if(myCourses.get(7).is4ab==true){
                writer.write(myCourses.get(7).name + '\n');
                writer.write(myCourses.get(7).name + '\n');
                writer.write("Free" + '\n');
            }else{
                writer.write("Free" + '\n');
                writer.write(myCourses.get(7).name + '\n');
                writer.write(myCourses.get(7).name + '\n');
            }
            if(myCourses.get(7).is4ab == false && myCourses.get(7).isScience == true){
                writer.write(myCourses.get(7).name + '\n');
            }else {
                writer.write(myCourses.get(2).name + '\n');
            }
            writer.write(myCourses.get(5).name + '\n');



            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }









        Toast.makeText(getApplicationContext(), "Schedule Added",   Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }
    public void clearSchedule(View view){
        try {
            OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "period4Courses", Context.MODE_PRIVATE));
            writerTwo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "allDaysSchedule", Context.MODE_PRIVATE));
            writerTwo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] fileNames = {"day1Schedule","day2Schedule","day3Schedule","day4Schedule","day5Schedule","day6Schedule","day7Schedule","day8Schedule"};
        for(int i = 0; i<fileNames.length; i++){
            try {
                OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                        fileNames[i], Context.MODE_PRIVATE));
                writerTwo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i<myCourses.size();i++){
            myCourses.get(i).name = "";
            myCourses.get(i).is4ab = false;
            myCourses.get(i).isScience=false;
            myCBs.get(i).setChecked(false);
        }
        setupEditTexts();
        Toast.makeText(getApplicationContext(), "Schedule Cleared",   Toast.LENGTH_LONG).show();
    }
}

