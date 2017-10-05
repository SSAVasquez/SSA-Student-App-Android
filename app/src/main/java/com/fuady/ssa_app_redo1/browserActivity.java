package com.fuady.ssa_app_redo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by fuady on 9/23/2017.
 */

public class browserActivity extends AppCompatActivity{
    public WebView webView;
    public String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        webView = (WebView)findViewById(R.id.browserView);
        Bundle extras = getIntent().getExtras();
        newString= extras.getString("STRING_I_NEED");
                    /*
                    webView.setWebViewClient(new MyWebViewClient());
                    webView.loadUrl(fullURLS[position]);
                    */
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
//This statement is used to enable the execution of JavaScript.

        webView.setVerticalScrollBarEnabled(false);
//This statement hides the Vertical scroll bar and does not remove it.

        webView.setHorizontalScrollBarEnabled(false);
//This statement hides the Horizontal scroll bar and does not remove it.

        webView.loadUrl(newString);


    }
    @Override
    protected void onResume(){
        super.onResume();
        setContentView(R.layout.web_layout);
        webView = (WebView)findViewById(R.id.browserView);
        Bundle extras = getIntent().getExtras();
        newString= extras.getString("STRING_I_NEED");
                    /*
                    webView.setWebViewClient(new MyWebViewClient());
                    webView.loadUrl(fullURLS[position]);
                    */
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
//This statement is used to enable the execution of JavaScript.

        webView.setVerticalScrollBarEnabled(false);
//This statement hides the Vertical scroll bar and does not remove it.

        webView.setHorizontalScrollBarEnabled(false);
//This statement hides the Horizontal scroll bar and does not remove it.

        webView.loadUrl(newString);


    }
    public void onBackPressed(){
        //if(!pdfViewing){
        if(webView.canGoBack()) {
            webView.goBack();
            //}

        }
    }
    public void back(View view){
        setContentView(R.layout.others);

    }
}
