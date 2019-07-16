package com.stone.taopiaopiao;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import com.stone.plugin.PayInterfaceActivity;
import org.jetbrains.annotations.NotNull;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-15 23:42
 */
public class ActivityBase extends Activity implements PayInterfaceActivity {

    private Activity that;


    @Override
    public void proxyAttach(Activity proxyActivity) {
        this.that = proxyActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Intent getIntent() {
        Intent var1;
        if (this.that != null) {
            Activity activity = this.that;
            var1 = activity.getIntent();
        } else {
            var1 = super.getIntent();
        }
        return var1;
    }

    public void setIntent(@Nullable Intent newIntent) {
        if (this.that != null) {
            this.that.setIntent(newIntent);
        } else {
            super.setIntent(newIntent);
        }
    }

    @NotNull
    public Window getWindow() {
        if (this.that != null) {
            return this.that.getWindow();
        }
        return super.getWindow();

    }

    @NotNull
    public WindowManager getWindowManager() {
        if (this.that != null) {
            return this.that.getWindowManager();
        }
        return super.getWindowManager();

    }

    @Nullable
    public View getCurrentFocus() {
        if (this.that != null) {
            return this.that.getCurrentFocus();
        }
        return super.getCurrentFocus();
    }

    @TargetApi(29)
    public void registerActivityLifecycleCallbacks(@NotNull Application.ActivityLifecycleCallbacks callback) {
        if (this.that != null) {
            this.that.registerActivityLifecycleCallbacks(callback);
        } else {
            super.registerActivityLifecycleCallbacks(callback);
        }
    }

    @TargetApi(29)
    public void unregisterActivityLifecycleCallbacks(@NotNull Application.ActivityLifecycleCallbacks callback) {
        if (this.that != null) {
            this.that.unregisterActivityLifecycleCallbacks(callback);
        } else {
            super.unregisterActivityLifecycleCallbacks(callback);
        }
    }

    public void setContentView(int layoutResID) {
        if (this.that != null) {
            this.that.setContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
    }

    public void setContentView(@Nullable View view) {
        if (this.that != null) {
            this.that.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    @Nullable
    public View findViewById(@IdRes int id) {
        if (this.that != null) {
            return this.that.findViewById(id);
        }
        return super.findViewById(id);
    }

    @TargetApi(29)
    public void setTheme(@Nullable Resources.Theme theme) {
        if (this.that != null) {
            this.that.setTheme(theme);
        } else {
            super.setTheme(theme);
        }
    }

    public void setTheme(int resid) {
        if (this.that != null) {
            this.that.setTheme(resid);
        } else {
            super.setTheme(resid);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onReStart() {

    }

    @Override
    public void onPostCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPostResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {

    }
}
