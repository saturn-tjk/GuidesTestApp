package com.example.guidestestapp.guidedetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.guidestestapp.R;

public class GuideDetailActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://guidebook.com";

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);

        String url = getIntent().getStringExtra("url");
        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebViewKlient());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(BASE_URL+url);
    }
}
