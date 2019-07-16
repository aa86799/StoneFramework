package com.stone.framework

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = "/app/proxy")
class ProxyActivity : Activity() {

    @JvmField
    @Autowired
    var className: String? = null //    需要加载淘票票的  类名
//    var payInterfaceActivity: PayInterfaceActivity? = null

    // taopiaopiao / MainActivity
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().inject(this)
    }

//        className = intent.getStringExtra("className")
//
//        try {
//            val activityClass = classLoader!!.loadClass(className)
//            val constructor = activityClass.getConstructor()
//            val instance = constructor.newInstance()
//
//            payInterfaceActivity = instance as PayInterfaceActivity
//
//            payInterfaceActivity?.proxyAttach(this)
//            val bundle = Bundle()
//            payInterfaceActivity?.onCreate(bundle)
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
//
//    override fun startActivity(intent: Intent) {
//        val className1 = intent.getStringExtra("className")
//        val intent1 = Intent(this, ProxyActivity::class.java)
//        intent1.putExtra("className", className1)
//        super.startActivity(intent1)
//    }
//
//    override fun startService(service: Intent): ComponentName? {
//        val serviceName = service.getStringExtra("serviceName")
//        val intent1 = Intent(this, ProxyService::class.java)
//        intent1.putExtra("serviceName", serviceName)
//        return super.startService(intent1)
//    }
//
//    override fun getClassLoader(): ClassLoader? {
//        return PluginManager.dexClassLoader
//    }
//
//    override fun getResources(): Resources {
//        return PluginManager.resources!!
//    }
//
//
//    override fun onStart() {
//        super.onStart()
//        payInterfaceActivity?.onStart()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        payInterfaceActivity?.onDestroy()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        payInterfaceActivity?.onPause()
//    }
}
