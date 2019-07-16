package com.stone.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;

/**
 * desc   : 对应 Activity 的生命周期方法、touch 事件、back 事件
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019-07-15 16:39
 */

public interface PayInterfaceActivity {
    void proxyAttach(Activity proxyActivity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onReStart();

    void onPostCreate(); //start 5.0

    void onResume();

    void onPostResume(); //start 5.0

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();
}
