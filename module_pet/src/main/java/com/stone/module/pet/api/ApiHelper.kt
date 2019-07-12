package com.stone.module.pet.api

import com.blankj.utilcode.util.AppUtils
import component.App
import okhttp3.Interceptor
import retrofit2.Retrofit

object ApiHelper {
    private var retrofit: Retrofit? = null

    val URL_BASE = "https://easy-mock.com/mock/5d28090cd6c6fc494347a13b/RestServer/stoneframework/"
//    val URL_BASE = "http://192.168.1.115:8082"
    val URL_BASE_DOMAIN = "URL_BASE"

    fun service():ApiService {
        if (retrofit == null) {
            println("ApiHelper#retrofit 初始化")
            val interceptors: List<Interceptor> = listOf(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Requested-With", "XMLHttpRequest")
                    /*
                         *　User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统
                         * 　及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                         *  这里标记为 android 设备
                         */
                    .addHeader(
                        "User-Agent",
                        "jw_app_android_" + AppUtils.getAppVersionCode(App.getInstance()!!.packageName)
                    )
                    .build()
                chain.proceed(request)
            })
            retrofit = SRetrofit.get(App.getInstance(), URL_BASE, interceptors)
        }
        return retrofit?.create(ApiService::class.java)!!
    }
}