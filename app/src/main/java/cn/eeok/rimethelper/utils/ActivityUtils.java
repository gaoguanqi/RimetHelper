package cn.eeok.rimethelper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ActivityUtils {
    /** 通过Class跳转界面 **/
    public static void startActivity(Context ctx,Class<?> cls) {
        Intent intent = new Intent(ctx, cls);
        ctx.startActivity(intent);
    }

    /** 通过Class跳转界面 **/
    public static void startActivityNoBack(Context ctx, Class<?> cls) {
        Intent intent = new Intent();
        startActivity(ctx,cls);
        ((Activity) ctx).finish();
    }
    /** 含有Bundle通过Class跳转界面 **/
    public static void startActivity(Context ctx, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(ctx,cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ctx.startActivity(intent);
    }




    /** 含有Bundle通过Class跳转界面 **/
    public static void startActivityNoBack(Context ctx, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ctx.startActivity(intent);
        ((Activity) ctx).finish();
    }
}
