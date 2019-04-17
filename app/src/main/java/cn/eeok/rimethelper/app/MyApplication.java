package cn.eeok.rimethelper.app;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import cn.eeok.rimethelper.home.TimeReceiver;
import cn.eeok.rimethelper.utils.Utils;


/**
 * Created by Administrator on 2017/2/24.
 */
public class MyApplication extends Application {
    private static MyApplication mAppCtx;

    private TimeReceiver mTimeReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
        //注册时钟广播
        registerTimeReceiver();
    }

    private void initConfig() {
        mAppCtx = this;
        Utils.init(this);
    }

    // 单例模式中获取唯一的MyApplication实例
    public static MyApplication getContext() {
        return mAppCtx;
    }


    private void registerTimeReceiver() {
        IntentFilter timeFilter = new IntentFilter();
        timeFilter.addAction(Intent.ACTION_TIME_TICK);
        mTimeReceiver = new TimeReceiver();
        registerReceiver(mTimeReceiver, timeFilter);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        unregisterReceiver(mTimeReceiver);
    }
}



