package com.jinq.widgetsx.application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.jinq.widgetsx.util.ActivityLifecycleHandler;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {

    public static Application application;
//    public static User user;
    public static List<Activity> activitys = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        user = new User();

        Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程  以下用来捕获程序崩溃异常
        //TODO　thread leaked?
        registerActivityLifecycleCallbacks(new ActivityLifecycleHandler());

    }

    public static Application getApplication() {
        return application;
    }

    public static void exit() {
        for (Activity ac : activitys) {
            ac.finish();
        }
    }

    // 创建服务用于捕获崩溃异常
    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();
            restartApp();//发生崩溃异常时,重启应用
        }
    };
    //重启App
    public void restartApp(){
//        exit();
//        Intent intent = new Intent(this,SplashActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        this.startActivity(intent);
    }

}
