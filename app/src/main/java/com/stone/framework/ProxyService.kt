//package com.stone.framework
//
//import android.app.Service
//import android.content.Intent
//import android.os.Bundle
//import android.os.IBinder
//import androidx.annotation.Nullable
//import com.stone.plugin.PayInterfaceService
//
//
//class ProxyService : Service() {
//    var serviceName: String? = null
//    var payInterfaceService: PayInterfaceService? = null
//
//    @Nullable
//    override fun onBind(intent: Intent): IBinder? {
//        init(intent)
//        return null
//    }
//
//    private fun init(intent: Intent) {
//        serviceName = intent.getStringExtra("serviceName")
//        //        class
//        try {
//            val loadClass = PluginManager.dexClassLoader?.loadClass(serviceName)
//
//            val localConstructor = loadClass?.getConstructor()
//            val instance = localConstructor?.newInstance()
//
//            payInterfaceService = instance as PayInterfaceService
//            payInterfaceService!!.attach(this)
//            val bundle = Bundle()
//            bundle.putInt("form", 1)
//            payInterfaceService!!.onCreate()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
//
//    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        if (payInterfaceService == null) {
//            init(intent)
//        }
//
//        return payInterfaceService!!.onStartCommand(intent, flags, startId)
//    }
//
//    override fun onDestroy() {
//        payInterfaceService!!.onDestroy()
//        super.onDestroy()
//
//    }
//
//    override fun onLowMemory() {
//        payInterfaceService!!.onLowMemory()
//        super.onLowMemory()
//    }
//
//
//    override fun onUnbind(intent: Intent): Boolean {
//        payInterfaceService!!.onUnbind(intent)
//        return super.onUnbind(intent)
//    }
//
//    override fun onRebind(intent: Intent) {
//        payInterfaceService!!.onRebind(intent)
//        super.onRebind(intent)
//    }
//
//}
