package com.weiboyi.routerdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
    }

    public static MainActivity getActivity() {
        return activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_nav:
                //简单跳转
                ARouter.getInstance().build(Navigation.ACTION_NORMAL)
                        .withString("name", "param values")
                        .navigation();
                //通过url打开，效果同上面一样
//                Uri u = Uri.parse("/nav/normal?name=param values");
//                ARouter.getInstance().build(u)
//                        .navigation();
                break;
            case R.id.a_module_nav:
                //跳转到其他模块
                Uri u = Uri.parse("/modulea/main_page?text=hello module");
                ARouter.getInstance().build(u)
                        .navigation();
                break;
            case R.id.b_module_nav:
                //跳转到其他模块
                ARouter.getInstance().build(Uri.parse("/moduleb/main_page?text=hello module"))
                        .navigation();
                break;
            case R.id.for_result_nav:
                //处理forResult
                ARouter.getInstance()
                        .build(Navigation.ACTION_FOR_RESULT)
                        .navigation(this, 8);
                break;
            case R.id.nav_filter:
                ARouter.getInstance()
                        .build(Navigation.ACTION_NAV_FILTER)
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {

                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.d("ARouter", "被拦截了");
                            }
                        });
                break;
            case R.id.nav_webview:
                ARouter.getInstance()
                        .build(Navigation.ACTION_NAV_WEBVIEW)
                        .withString("url", "file:///android_asset/web_scheme.html")
                        .navigation();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 8:
                if (resultCode == 99) {
                    String content = data.getStringExtra("content");
                    Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
