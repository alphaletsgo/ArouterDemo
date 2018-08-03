package com.weiboyi.routerdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.weiboyi.routerdemo.Navigation;
import com.weiboyi.routerdemo.R;

@Route(path = Navigation.ACTION_FOR_RESULT)
public class ForResultActivity extends AppCompatActivity {
private TextView textTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);
         textTest =  findViewById(R.id.id_text);
         textTest.setText("new b");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("content","The result from front page!");
        this.setResult(99,intent);
        super.onBackPressed();
    }
}
