package com.stone.framework

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources

import java.io.File

import dalvik.system.DexClassLoader

object PluginManager {
    var packageInfo: PackageInfo? = null
        private set

    @JvmField
    var resources: Resources? = null

    private var context: Context? = null
    var dexClassLoader: DexClassLoader? = null

    fun setContext(context: Context) {
        this.context = context
    }

    fun loadPath(context: Context) {

        val filesDir = context.getDir("plugin", Context.MODE_PRIVATE)
        val name = "plugint.apk"
        val path = File(filesDir, name).absolutePath

        val packageManager = context.packageManager
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)


        //        activity 名字
        val dexOutFile = context.getDir("dex", Context.MODE_PRIVATE)
        //dex/apk 路径，缓存目录 odex 缓存文件目录，
        dexClassLoader = DexClassLoader(path, dexOutFile.absolutePath, null, context.classLoader)
        //        resource

        try {
            val assetManager = AssetManager::class.java.newInstance()

            val addAssetPath = AssetManager::class.java.getMethod("addAssetPath", String::class.java)
            addAssetPath.invoke(assetManager, path)
            resources = Resources(assetManager, context.resources.displayMetrics, context.resources.configuration)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
