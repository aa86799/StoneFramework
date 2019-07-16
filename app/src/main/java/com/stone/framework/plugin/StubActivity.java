package com.stone.framework.plugin;

import android.app.Activity;
import android.os.Bundle;

public class StubActivity extends Activity {
    public static final String TARGET_COMPONENT = "TARGET_COMPONENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("sotne-> StubActivity#oncreate");
    }
}