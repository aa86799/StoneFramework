package com.stone.framework.plugin.util;

import com.blankj.utilcode.util.LogUtils;
import com.stone.framework.App;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * desc:    加载插件资源 还是有问题，对于  插件中使用 AppCompatActivity 来说
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-16 16:40
 */
public class LoadApkResDir {
    public static void switchToPlugResources(String resPath) {
        try {
            String packageName = App.Companion.instance().getPackageName();
            //获取LoadedApk的Class
            Class<?> loadApkCls = Class.forName("android.app.LoadedApk");
            //获取ActivityThread的Class
            Class<?> activityThreadCls = Class.forName("android.app.ActivityThread");

            //获取ActivityThread对象
            Method currentActivityThreadMethod = activityThreadCls.getMethod("currentActivityThread");
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

            //反射获取mPackages中的LoadedApk
            Field filed = activityThreadCls.getDeclaredField("mPackages");
            filed.setAccessible(true);
            Map mPackages = (Map) filed.get(currentActivityThread);
            WeakReference wr = (WeakReference) mPackages.get(packageName);

            Field filed2 = loadApkCls.getDeclaredField("mResDir");
            filed2.setAccessible(true);
            filed2.set(wr.get(), resPath);
        } catch (Exception e) {
            LogUtils.e("changeResDir:" + e.toString());
        }
    }
}
