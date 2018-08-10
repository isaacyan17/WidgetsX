/*
 * ========================================================
 * Copyright(c) 2017 杭州贤芯科技-版权所有
 * ========================================================
 * 本软件由杭州贤芯科技所有, 未经书面许可, 任何单位和个人不得以
 * 任何形式复制代码的部分或全部, 并以任何形式传播。
 * 公司网址
 *
 * 			http://www.senthink.com/
 *
 * ========================================================
 */

package com.jinq.widgetsx.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jinq.widgetsx.constant.AppConstant;

import java.util.Date;


/**
 * @author : Joan Yu
 * @date   : 2014-2-28
 * @desc   : SharedPreferences工具类
 * 
 * 	public method
 * 	<li>putString(Context, String, String)			保存字符串数据 </li>
 * 	<li>getString(Context, String, String)			获取字符串数据 </li>
 * 	<li>putInt(Context, String, int)				保存整型数据 </li>
 * 	<li>getInt(Context, String, int)				获取整型数据 </li>
 * 	<li>putLong(Context, String, long)				获取长整型数据 </li>
 * 	<li>getLong(Context, String, long)				获取长整型数据 </li>
 * 	<li>putBoolean(Context, String, boolean)		获取布尔值数据 </li>
 * 	<li>getBoolean(Context, String, boolean)		获取布尔值数据 </li>
 * 	<li>putFloat(Context, String, float)			获取浮点型数据 </li>
 * 	<li>getFloat(Context, String, float)			获取浮点型数据 </li>
 * 
 */
public class PrefUtil {
	/**
	 * 保存字符串数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String key, String value) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	/**
	 * 获取字符串数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		return pref.getString(key, defValue);
	}
	
	
	/**
	 * 保存整型数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putInt(Context context, String key, int value) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	/**
	 * 保存整型数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		return pref.getInt(key, defValue);
	}
	
	
	/**
	 * 保存长整型数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putLong(Context context, String key, long value) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	/**
	 * 保存长整型数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		return pref.getLong(key, defValue);
	}
	
	/**
	 * 保存布尔值数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putBoolean(Context context, String key, boolean value) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	/**
	 * 保存布尔值数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		return pref.getBoolean(key, defValue);
	}
	
	
	/**
	 * 保存浮点型数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putFloat(Context context, String key, float value) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putFloat(key, value);
		editor.commit();
	}
	
	/**
	 * 保存浮点型数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, float defValue) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		return pref.getFloat(key, defValue);
	}

	public static void remove(Context context, String key) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.remove(key);
		editor.commit();
	}

	public static void clear(Context context) {
		SharedPreferences pref = context
				.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}

	/***
	 * 用户信息是否失效
	 * @return
	 */
	public static boolean getLoginState(Context context){
		if (context == null)
			return false;
		SharedPreferences sp = context.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		String account = sp.getString("account", null);
		long stamp = sp.getLong("account_stamp", 0);
		long time = new Date().getTime();
		if(account != null)
			if ((time - stamp) / 1000 < 3600)
				return true;
		return false;
	}

	public static void setLoginState(Context context,String account){
		if(context == null)
			return ;
		SharedPreferences sp = context.getSharedPreferences(AppConstant.AGNETTY, Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		edit.putString("account", account);
		edit.putLong("account_stamp",new Date().getTime());
		edit.commit();
	}
}
