package cn.eeok.rimethelper.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.eeok.rimethelper.MainActivity;


/**
 * Created by Administrator on 2017/3/28.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Intent autoStart = new Intent(context, MainActivity.class);

            autoStart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(autoStart);
            //根据标签开启服务
//		    context.startService(new Intent("com.bskj.showsomething.AutorunService"));

        }

    }

}
