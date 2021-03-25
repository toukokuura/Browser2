package com.example.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText url;
    Button siirrybtn, shoutoutbtn, backbtn;
    ImageButton reload;
    WebView web;
    Context context;

    String currentSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        url = (EditText) findViewById(R.id.editURL);
        siirrybtn = (Button) findViewById(R.id.button);
        shoutoutbtn = (Button) findViewById(R.id.button2);
        backbtn = (Button) findViewById(R.id.button3);
        reload = (ImageButton) findViewById(R.id.imageButton);
        web = (WebView) findViewById(R.id.web);

        // permissions
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        web.setWebViewClient(new WebViewClient());

        //permission for javascript
        web.getSettings().setJavaScriptEnabled(true);

        //web.loadUrl("https://skinfo.dy.fi");

        siirrybtn.setOnClickListener(v -> {
            //siirry sivustolle tai renderöi tiedosto
            currentSite = "https://" + url.getText().toString();
            System.out.println(currentSite);


            if (currentSite == null || currentSite.equals("https://")) {
                System.out.println("No address given");

            } else if (currentSite.equals("https://index.html")) {
                System.out.println("Loading file");
                web.loadUrl("file:///android_asset/index.html");

            } else {
                System.out.println("Loading site");
                web.loadUrl(currentSite);
            }
        });

        shoutoutbtn.setOnClickListener(v-> {
            //run shoutOut() from index.html
            web.evaluateJavascript("javascript:shoutOut()",null);
        });

        backbtn.setOnClickListener(v-> {
            //run initialize() from index.html
            web.evaluateJavascript("javascript:initialize()",null);
        });

        reload.setOnClickListener(v -> {
            //päivitä tämänhetkinen sivu
            if (currentSite != null && !currentSite.equals("https://") && !currentSite.equals("https://index.html")) {
                web.loadUrl(currentSite);
            }
        });

    }

}