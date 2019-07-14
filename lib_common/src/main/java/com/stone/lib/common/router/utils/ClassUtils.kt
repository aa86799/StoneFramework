package com.stone.lib.common.router.utils


import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.stone.lib.common.router.DefaultPoolExecutor
import dalvik.system.DexClassLoader
import dalvik.system.DexFile
import java.io.IOException
import java.util.*
import java.util.concurrent.CountDownLatch

object ClassUtils {

    /**
     * 获得程序所有的apk(instant run会产生很多split apk) 路径
     */
    @Throws(PackageManager.NameNotFoundException::class, IOException::class)
    fun getSourcePaths(context: Context): List<String> {
        val applicationInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
        val sourcePaths = ArrayList<String>()
        sourcePaths.add(applicationInfo.sourceDir)
        //instant run
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (null != applicationInfo.splitSourceDirs) {
                sourcePaths.addAll(listOf(*applicationInfo.splitSourceDirs))
            }
        }
        return sourcePaths
    }

    /**
     * 路由表
     *
     * @param context
     * @param packageName
     * @return
     * @throws PackageManager.NameNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    @Throws(PackageManager.NameNotFoundException::class, IOException::class, InterruptedException::class)
    fun getFileNameByPackageName(context: Application, packageName: String): Set<String> {
        val classNames = HashSet<String>()
        val paths = getSourcePaths(context)
        //使用同步计数器判断处理完成
        val parserCtl = CountDownLatch(paths.size)
        val threadPoolExecutor = DefaultPoolExecutor.newDefaultPoolExecutor(paths.size)
        for (path in paths) {
            threadPoolExecutor!!.execute {
                var dexFile: DexFile? = null
                try {
                    //加载 apk中的dex 并遍历 获得所有包名为 {packageName} 的类
                    dexFile = DexFile(path)
                    val dexEntries = dexFile.entries()
                    while (dexEntries.hasMoreElements()) {
                        val className = dexEntries.nextElement()
                        if (className.startsWith(packageName)) {
                            classNames.add(className)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    if (null != dexFile) {
                        try {
                            dexFile.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    }
                    //释放1个
                    parserCtl.countDown()
                }
            }
        }
        //等待执行完成
        parserCtl.await()
        return classNames
    }
}
