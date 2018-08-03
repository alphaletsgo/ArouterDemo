package com.weiboyi.routerdemo.filter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.net.URI;

public class SchemeFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        Log.d("SchemeFilterActivity","uri-"+uri.toString());
        String path = uri.toString().split("//")[1];
        path = "/"+path;
        Log.d("SchemeFilterActivity","path"+path);
        Uri u = Uri.parse(path);

        ARouter.getInstance().build(u).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }
        });
    }

}
