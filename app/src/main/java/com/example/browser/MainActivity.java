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
    Button siirrybtn;
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
        reload = (ImageButton) findViewById(R.id.imageButton);
        web = (WebView) findViewById(R.id.web);

        // permissions
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        web.setWebViewClient(new WebViewClient());

        //permission for javascript
        web.getSettings().getJavaScriptEnabled();

        //web.loadUrl("https://skinfo.dy.fi");

        siirrybtn.setOnClickListener(v -> {
            //siirry sivustolle
            currentSite = "https://" + url.getText().toString();

            if (currentSite == null || currentSite == "https://") {
                System.out.println("No address given");
            } else {
                web.loadUrl(currentSite);
            }
        });

        reload.setOnClickListener(v -> {
            //päivitä tämänhetkinen sivu
            if (currentSite != null && currentSite == "https://") {
                web.loadUrl(currentSite);
            }
        });

    }

}