package com.example.owner.second_application_java2018.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.example.owner.second_application_java2018.R;


public class WebViewActivity extends Activity {

    private boolean flag=false;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView= (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.carrentals.com");
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack())
        {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        finish();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(flag)//if it is coming back from the webView, we don't want to see this activity- go back to main Activity
            finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag=true;

    }
}

