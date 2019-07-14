package com.stone.modulepetstandalone

import android.app.Application
import com.stone.module.pet.config.GlobalConfig
import io.github.prototypez.appjoint.core.ModuleSpec

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 13:55
 */
@ModuleSpec
class ModulePetApp : Application() {

    companion object {
        //静态内部类
        private var instance: ModulePetApp? = null

        fun getInstance(): ModulePetApp? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        initLeakCanary()

        initARouter()

//        val petService = PetServiceImpl()
        val petService = GlobalConfig.PET_SERVICE

        petService.setApplication(this)
    }

    private fun initLeakCanary() {
//        LeakSentry.config = LeakSentry.config.copy(watchFragmentViews = false)
//        LeakCanary.config = LeakCanary.config.copy(dumpHeap = false)

        /*
         * Watching objects with a lifecycle:
         *      LeakSentry.refWatcher.watch(this)
         */
    }

    private fun initARouter() {
//        if (BuildConfig.DEBUG) {// 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog()    // 打印日志
////            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this)

    }

}