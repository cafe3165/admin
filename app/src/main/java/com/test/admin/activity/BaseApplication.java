package com.test.admin.activity;

import android.app.Application;

/**
 * 文件名 BaseApplication
 * 描述
 * 版本信息，版本号 v1.0
 * 创建日期 2016/12/20
 * 版权声明 Created by ZengYinan
 */

public class BaseApplication extends Application{
    private static BaseApplication instance;
    private boolean isNight;

    public BaseApplication() {
        isNight = false;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        isNight = SharedPreferencesUtils.getBoolean(this, "isNight", false);
    }

    public boolean getIsNight() {
        return isNight;
    }

    public void setIsNight(boolean night) {
        isNight = night;
        SharedPreferencesUtils.saveBoolean(this, "isNight", night);
    }
}
