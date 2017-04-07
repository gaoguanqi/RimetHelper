package cn.eeok.rimethelper.utils;

import android.util.Log;

public class LogUtil {

	private static final boolean flag = true;
	
	public static void e(String tag,String msg){
		if(flag){
			Log.e(tag, msg);
		}
	}
}
