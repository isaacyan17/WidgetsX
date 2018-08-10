package com.jinq.widgetsx.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * 检测应用是否在前台
 */
public class ActivityLifecycleHandler implements Application.ActivityLifecycleCallbacks {
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;
    private static int destroyed;
    private static int created;


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ++created;
        Log.e("LifecycleHandler","onActivityCreated: " +  created+"  ::"+destroyed);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        Log.e("LifecycleHandler","application is in foreground: " + (resumed > paused));
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
//        Log.e("LifecycleHandler","application is in visible: " +  (started > stopped));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ++destroyed;
        Log.e("LifecycleHandler","onActivityDestroyed: " +  destroyed+"  ::"+created);
    }
    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public static boolean isApplicationInForeground() {
        // 当所有 Activity 的状态中处于 resumed 的大于 paused 状态的，即可认为有Activity处于前台状态中
        return resumed > paused;
    }

    public static boolean isApplicationDestroyed() {

        return !isApplicationInForeground() && destroyed >= created;
    }
}
