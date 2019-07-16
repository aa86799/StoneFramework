package com.stone.framework

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.stone.module.pet.config.PetGlobalConfig
import com.stone.module.pet.config.PetGlobalConfig.app

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 08:58
 */
class App : Application() {

    private val app = this

    companion object {
        fun instance(): Application? {
            return app
        }
    }
    override fun onCreate() {
        super.onCreate()

        PetGlobalConfig.init(this)

        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {// 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()    // 打印日志
//            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }
}