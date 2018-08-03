package com.weiboyi.routerdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.weiboyi.routerdemo.Navigation;
import com.weiboyi.routerdemo.R;

@Route(path = Navigation.ACTION_NORMAL)
public class NormalActivity extends AppCompatActivity {
    @Autowired(name = "name")
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ARouter.getInstance().inject(this);//如果使用注解Autowired的方式获取参数值得话，需要调用该语句，也可以使用其他方式获取参数，详情https://github.com/alibaba/ARouter/blob/master/README_CN.md
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }
}
