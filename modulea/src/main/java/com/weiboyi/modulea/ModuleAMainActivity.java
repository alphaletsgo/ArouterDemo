package com.weiboyi.modulea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/modulea/main_page")
public class ModuleAMainActivity extends AppCompatActivity {
    TextView subTitle;
    @Autowired(name = "text")
    String text = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_amain);
        ARouter.getInstance().inject(this);
        subTitle = findViewById(R.id.title);
        subTitle.setText(text);

    }
}
