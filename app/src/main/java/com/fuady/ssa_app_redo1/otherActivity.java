package com.fuady.ssa_app_redo1;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fuady on 9/3/2017.
 */

public class otherActivity extends AppCompatActivity /*implements View.OnClickListener*/{
    public WebView webView;
    public boolean pdfViewing = false;

    private FirebaseAuth firebaseAuth;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others);
        setupListView();
        /*firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, signInActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


        Logout = (Button) findViewById(R.id.Logout);
        //Logout.setOnClickListener(this);*/

    }
    public void signOut(View view){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, signInActivity.class));
    }

    /*@Override
    public void onClick(View view) {
        if(view == Logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, signInActivity.class));
        }
    }
    */
    @Override
    protected void onResume(){
        super.onResume();
        setContentView(R.layout.others);
        setupListView();
    }


    public void viewFriends(View view){
        Intent myIntent = new Intent(getApplicationContext(), friendActivity.class);
        // finish();
        startActivity(myIntent);
    }
    public void viewBarcode(View view){
        Intent myIntent = new Intent(getApplicationContext(), barcodeActivity.class);
        // finish();
        startActivity(myIntent);
    }
    public void viewSchedule(View view){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        //finish();
        startActivity(myIntent);
        //setContentView(R.layout.activity_main);
    }
    public void setupListView() {
        final String[] fullURLS = {"https://sites.google.com/site/ssamobilehelper/schooldaysinfo/Daily%20Bulletin.pdf?attredirects=0&d=1"/*"https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxzc2Ftb2JpbGVoZWxwZXJ8Z3g6NTE2ZWE2ZDAzZmUxNzcwNQ"*/, /*"https://www.shadysideacademy.org/lunch-menus",*/ "https://www.shadysideacademy.org/about/news","https://www.shadysideacademy.org/students/ms-ss-student-portal", "https://www.shadysideacademy.org", "https://www.shadysideacademy.org/about/calendar"  };

        final ArrayList<String> linkNames = new ArrayList<>();
        linkNames.add("Daily Bulletin");
        //linkNames.add("Lunch Menu");
        linkNames.add("SSA News");
        linkNames.add("Teacher Page");
        linkNames.add("Campus Map");
        linkNames.add("SSA Calendar");
        linkNames.add("About");
        ListView listDays = (ListView) findViewById(R.id.linkView);
        /*
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, linkNames);
        // Set The Adapter
        listDays.setAdapter(arrayAdapter);
// register onClickListener to handle click events on each item
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, final int position, long arg3)
            {
                if(position!= 6) {
                    setContentView(R.layout.web_layout);
                    WebView webView = (WebView)findViewById(R.id.browserView);
                    webView.setWebViewClient(new MyWebViewClient());
                    webView.loadUrl(fullURLS[position]);


                }else{
                    setContentView(R.layout.about_page);
                }


            }
        });
        */

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] linkicons = new int[]{
                R.drawable.bulletinicon,
               // R.drawable.lunchmenuicon,
                R.drawable.newsicon,
                R.drawable.teacherpageicon,
                R.drawable.mapicon,
                R.drawable.teacherpageicon,
                R.drawable.bulletinicon
        };
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<linkNames.size();i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "  " + linkNames.get(i));

            hm.put("flag", Integer.toString(linkicons[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file


        // Setting the adapter to the listView
        listDays.setAdapter(adapter);
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, final int position, long arg3)
            {
                if(position!= 5 && position != 3 && position!=0) {
                    setContentView(R.layout.web_layout);
                    webView = (WebView)findViewById(R.id.browserView);
                    /*
                    webView.setWebViewClient(new MyWebViewClient());
                    webView.loadUrl(fullURLS[position]);
                    */
                    webView.setWebViewClient(new WebViewClient());

                    webView.getSettings().setJavaScriptEnabled(true);
                   // webView.getSettings().setPluginsEnabled(true);
                    webView.getSettings().setAllowFileAccess(true);

//This statement is used to enable the execution of JavaScript.

                    webView.setVerticalScrollBarEnabled(false);
//This statement hides the Vertical scroll bar and does not remove it.

                    webView.setHorizontalScrollBarEnabled(false);
//This statement hides the Horizontal scroll bar and does not remove it.

                    webView.loadUrl(fullURLS[position]);

                }else if(position == 5){
                    setContentView(R.layout.about_page);
                }else if(position==0){
                    startDownload();
                }
                else{
                    setContentView(R.layout.pdf_view);
                    pdfViewing = true;
                    PDFView pdfView;
                    pdfView = (PDFView)(findViewById(R.id.myPdf));
                    pdfView.fromAsset("campusmap.pdf").load();
                }


            }
        });

    }
    @Override
    public void onBackPressed(){
        //if(!pdfViewing){
        if(webView!= null && webView.canGoBack()) {
            webView.goBack();
        //}
        }else{
            back(null);
            pdfViewing = false;
        }

    }
    public void back(View view){
        setContentView(R.layout.others);
        setupListView();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);*/
            return false;
        }


    }
    public void sendNotification() {

//Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.appicon)
                        .setContentTitle("Daily Bulletin")
                        .setContentText("File Downloaded to Downloads Folder");


// Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//When you issue multiple notifications about the same type of event, it’s best practice for your app to try to update an existing notification with this new information, rather than immediately creating a new notification. If you want to update this notification at a later date, you need to assign it an ID. You can then use this ID whenever you issue a subsequent notification. If the previous notification is still visible, the system will update this existing notification, rather than create a new one. In this example, the notification’s ID is 001//

                mNotificationManager.notify(001, mBuilder.build());
    }
    public void startDownload() {
        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse("https://sites.google.com/site/ssamobilehelper/schooldaysinfo/Daily%20Bulletin.pdf?attredirects=0&d=1"));
        mRqRequest.setDescription("Daily Bulletin");
//  mRqRequest.setDestinationUri(Uri.parse("give your local path"));
        long idDownLoad=mManager.enqueue(mRqRequest);
        sendNotification();
        Toast.makeText(getApplicationContext(), "Downloading to Your Downloads Folder", Toast.LENGTH_LONG).show();
    }
}
