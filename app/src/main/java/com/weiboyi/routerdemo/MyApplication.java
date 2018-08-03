package com.weiboyi.routerdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by he on 2018/7/26.
 */

public class MyApplication extends Application {
    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
