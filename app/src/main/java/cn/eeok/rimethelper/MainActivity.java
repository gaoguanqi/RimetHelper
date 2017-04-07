package cn.eeok.rimethelper;

import android.os.Bundle;

import cn.eeok.rimethelper.base.BaseActivity;
import cn.eeok.rimethelper.home.HomeActivity;
import cn.eeok.rimethelper.utils.ActivityUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityUtils.startActivityNoBack(MainActivity.this,HomeActivity.class);
    }


}
