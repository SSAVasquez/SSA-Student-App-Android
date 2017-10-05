package com.fuady.ssa_app_redo1;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fuady on 8/27/2017.
 */


public class refreshSchedule extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        String strURL = "https://sites.google.com/site/ssamobilehelper/schooldaysinfo/keepnameunchanged.txt?attredirects=0&d=1";
        try {
           MainActivity.fullSchedule = readSchedule(strURL);
            System.out.println(Arrays.deepToString(MainActivity.fullSchedule));
        } catch (IOException e) {
            System.out.println("failed to connect to server");
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[]args) throws IOException {

        //int[][]schedule = readSchedule(strURL);
       // System.out.println(Arrays.deepToString(schedule));
    }
    public static int[][] readSchedule(String strURL) throws IOException{
        System.out.println("I did it I did it");
        System.out.println("I did it I did it");
        System.out.println("I did it I did it");
        System.out.println("I did it I did it");
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
        } finally {
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


}

