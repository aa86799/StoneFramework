package com.stone.framework

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import leakcanary.LeakCanary
import leakcanary.LeakSentry

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        initLeakCanary()

        initARouter()
    }

    private fun initLeakCanary() {
        LeakSentry.config = LeakSentry.config.copy(watchFragmentViews = false)
        LeakCanary.config = LeakCanary.config.copy(dumpHeap = false)

        /*
         * Watching objects with a lifecycle:
         *      LeakSentry.refWatcher.watch(this)
         */
    }

    private fun initARouter() {
        ARouter.init(this)
    }

}