package com.fuady.ssa_app_redo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by fuady on 7/4/2017.
 */
public class editScheduleActivity extends AppCompatActivity {
    public ArrayList<String> dayNames = new ArrayList<String>();
    public final static String ID_Extra = "com.fuady.SSA_APP_REDO";
    public final static String ID_EXTRA_NAME = "name";
    public final static String ID_EXTRA_PHONE_NUMBER = "number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_schedule_layout);
        setupListView();
    }
    public void setupListView() {
        dayNames.add("Add New Schedule");
        dayNames.add("Edit Day 1 Schedule");
        dayNames.add("Edit Day 2 Schedule");
        dayNames.add("Edit Day 3 Schedule");
        dayNames.add("Edit Day 4 Schedule");
        dayNames.add("Edit Day 5 Schedule");
        dayNames.add("Edit Day 6 Schedule");
        dayNames.add("Edit Day 7 Schedule");
        dayNames.add("Edit Day 8 Schedule");


        ListView listDays = (ListView) findViewById(R.id.dayList);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dayNames);
        // Set The Adapter
        listDays.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                String selectedmovie=dayNames.get(position);
                Toast.makeText(getApplicationContext(), selectedmovie+ " Selected",   Toast.LENGTH_LONG).show();
                if(position == 0) {
                    Intent myIntent = new Intent(getApplicationContext(), addScheduleActivity.class);
                    startActivity(myIntent);
                }else {
                    Intent myIntent = new Intent(getApplicationContext(), editDayScheduleActivity.class);
                    myIntent.putExtra(ID_Extra, position);
                    myIntent.putExtra(ID_EXTRA_NAME, dayNames.get(position));
                    startActivity(myIntent);
                }
            }
        });

    }

}