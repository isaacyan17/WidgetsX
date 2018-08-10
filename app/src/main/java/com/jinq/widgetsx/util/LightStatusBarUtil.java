package com.jinq.widgetsx.util;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Joan Yu
 * @ClassName LightStatusBarUtil
 * @Description TODO
 * @date 2017年08月29日 16:08
 */

public class LightStatusBarUtil {

    public static void setLightStatusBar(Activity activity, boolean dark) {
        switch (RomUtil.getLightStatausBarAvailableRomType()) {
            case RomUtil.AvailableRomType.MIUI:
                setMIUILightStatusBar(activity, dark);
                break;
            case RomUtil.AvailableRomType.FLYME:
                setFlymeLightStatusBar(activity, dark);
                break;
            case RomUtil.AvailableRomType.ANDROID_NATIVE:
                setAndroidNativeLightStatusBar(activity, dark);
                break;
            case RomUtil.AvailableRomType.NA:
                setAndroidNativeLightStatusBar(activity, false);
                break;
        }
    }

    private static boolean setMIUILightStatusBar(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            Log.e("applog","****************7");
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean setFlymeLightStatusBar(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(0);
        }
    }
}
