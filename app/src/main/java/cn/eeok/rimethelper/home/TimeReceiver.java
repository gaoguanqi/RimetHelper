package cn.eeok.rimethelper.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.Calendar;

import cn.eeok.rimethelper.event.Events;
import cn.eeok.rimethelper.event.RxBus;
import cn.eeok.rimethelper.utils.SPUtils;


public class TimeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);


        SPUtils mSP = new SPUtils("time");

        String openTime1 = mSP.getString("openTime1");
        String closeTime1 = mSP.getString("closeTime1");

        String openTime2 = mSP.getString("openTime2");
        String closeTime2 = mSP.getString("closeTime2");

        String openTime3 = mSP.getString("openTime3");
        String closeTime3 = mSP.getString("closeTime3");

        if(!TextUtils.isEmpty(openTime1) && !TextUtils.isEmpty(closeTime1) && !TextUtils.isEmpty(openTime2) && !TextUtils.isEmpty(closeTime2) && !TextUtils.isEmpty(openTime3) && !TextUtils.isEmpty(closeTime3)){

            String[] openTimeArr1 = openTime1.split(":");
            String[] closeTimeArr1 = closeTime1.split(":");

            String[] openTimeArr2 = openTime2.split(":");
            String[] closeTimeArr2 = closeTime2.split(":");

            String[] openTimeArr3 = openTime3.split(":");
            String[] closeTimeArr3 = closeTime3.split(":");


            //第一次
            if (hour == Integer.parseInt(openTimeArr1[0]) && min ==Integer.parseInt(openTimeArr1[1])) {
                RxBus.getInstance().send(Events.EVENT_CODE_TAP, "打开");
            }

            if (hour == Integer.parseInt(closeTimeArr1[0]) && min ==Integer.parseInt(closeTimeArr1[1])) {
                RxBus.getInstance().send(Events.EVENT_CODE_OTHER, "关闭");
            }


            //第2次
            if (hour == Integer.parseInt(openTimeArr2[0]) && min ==Integer.parseInt(openTimeArr2[1])) {
                RxBus.getInstance().send(Events.EVENT_CODE_TAP, "打开");
            }

            if (hour == Integer.parseInt(closeTimeArr2[0]) && min ==Integer.parseInt(closeTimeArr2[1])) {
                RxBus.getInstance().send(Events.EVENT_CODE_OTHER, "关闭");
            }

            //第3次
            if (hour == Integer.parseInt(openTimeArr3[0]) && min ==Integer.parseInt(openTimeArr3[1])) {
                RxBus.getInstance().send(Events.EVENT_CODE_TAP, "打开");
            }

            if (hour == Integer.parseInt(closeTimeArr3[0]) && min ==Integer.parseInt(closeTimeArr3[1])) {
                RxBus.getInstance().send(Events.EVENT_CODE_OTHER, "关闭");
            }
        }
    }
}
