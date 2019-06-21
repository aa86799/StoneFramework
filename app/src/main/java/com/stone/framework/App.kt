package com.stone.framework

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import leakcanary.LeakCanary
import leakcanary.LeakSentry

class App : Application() {

    companion object {//静态内部类
        private var instance: App? = null
        fun getInstance(): App? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

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