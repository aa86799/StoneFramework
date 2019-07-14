package com.stone.framework

import android.app.Application
import com.stone.lib.common.router.SRouter

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 08:58
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        SRouter.init(this)
    }
}