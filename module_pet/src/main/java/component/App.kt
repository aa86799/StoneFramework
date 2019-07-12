package component

import android.app.Application
import leakcanary.LeakCanary
import leakcanary.LeakSentry

class App : Application() {

    companion object {
        //静态内部类
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
//        if (BuildConfig.DEBUG) {// 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog()    // 打印日志
////            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this)
    }

}