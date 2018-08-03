package com.weiboyi.routerdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.weiboyi.routerdemo.Navigation;
import com.weiboyi.routerdemo.R;


@Route(path = Navigation.ACTION_NAV_WEBVIEW)
public class WebActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.web_content);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
