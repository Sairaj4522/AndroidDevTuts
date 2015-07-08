package com.thenewboston.travis.androiddevtuts;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

public class SimpleBrowser extends Activity implements OnClickListener {
    EditText url;
    WebView ourBrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Toolbar will now take on default Action Bar characteristics
        //SetActionBar (toolbar);
        //You can now use and reference the ActionBar
        //ActionBar.Title = "Hello from Toolbar";

        ourBrow = (WebView) findViewById(R.id.wvBrowser);
        //ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        ourBrow.getSettings().setUseWideViewPort(true);

        ourBrow.setWebViewClient(new ourViewClient());
        try {
            ourBrow.loadUrl("http://www.mybringback.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward = (Button) findViewById(R.id.bForward);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etURL);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bGo:
                String theWebSite = url.getText().toString();
                ourBrow.loadUrl(theWebSite);
                //hiding the Keyboard after using an EditText
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;

            case R.id.bBack:
                if (ourBrow.canGoBack())
                    ourBrow.goBack();
                break;

            case R.id.bRefresh:
                ourBrow.reload();
                break;

            case R.id.bForward:
                if (ourBrow.canGoForward())
                    ourBrow.goForward();
                break;

            case R.id.bHistory:
                ourBrow.clearHistory();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.simple_browser_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*switch(item.getItemId()){

            case R.id.aboutUs:
                Intent i = new Intent("com.thenewboston.travis.ABOUT");
                startActivity(i);
                break;
            case R.id.preferences:
                Intent pIntent = new Intent("com.thenewboston.travis.PREFS");
                startActivity(pIntent);
                break;
            case R.id.exit:
                finish();
                break;
        }*/

        return false;
    }
}
