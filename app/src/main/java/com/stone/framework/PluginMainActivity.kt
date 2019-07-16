package com.stone.framework

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.os.Environment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.stone.framework.plugin.ActivityThreadHookHelper
import com.stone.framework.plugin.LoadedApkAppInfo
import com.stone.framework.plugin.util.LoadApkResDir
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.*


/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-15 17:17
 */
class PluginMainActivity : AppCompatActivity() {

    private var apkPath: String? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        ActivityThreadHookHelper.doHandlerHook()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.app_activity_main)

        PluginManager.setContext(this)

        reqPermission()


        ActivityThreadHookHelper.doActivityStartHook(this)
    }

    fun reqPermission() {
        val rxPermissions = RxPermissions(this)

        //一个或多个权限，全部成功才成功；
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe({ permission ->
                if (permission!!) {
                    //("用户给权限啦")
                } else {
                    //("用户不给权限")
                }

            }, {

            })

        //一个或多个权限，分别单独处理
        rxPermissions.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            .subscribe { permission ->
                if (permission.name.equals(Manifest.permission.READ_EXTERNAL_STORAGE, ignoreCase = true)) {
                    when {
                        permission.granted -> //同意后调用
                            LogUtils.i("checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + true)
                        permission.shouldShowRequestPermissionRationale -> //禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                            LogUtils.e("checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false)
                        else -> //禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                            LogUtils.e("checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + false)
                    }
                }
            }

        //        rxPermissions.isGranted()  //返回 boolean： 某个权限是给授权
    }

    fun loadPlugin(view: View) {
        /* 创建私有目录(带包名的目录) 及其下的 插件 apk 文件；*/
        val filesDir = this.getDir("plugin", Context.MODE_PRIVATE)
        val name = "plugint.apk"
        val filePath = File(filesDir, name).path
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }

        /* 读取 sdcard 下的 插件 apk，写入到 私有目录下*/
        var inputStream: InputStream? = null
        var os: FileOutputStream? = null
        try {

            val dstFile = File(filePath)
            if (dstFile.exists()) dstFile.delete()

            LogUtils.iTag("stone->", "加载插件${File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name).path}")

            inputStream = FileInputStream(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name))
            os = FileOutputStream(filePath)

            var len = 0
            val buffer = ByteArray(1024)
            do {
                len = inputStream.read(buffer)
                if (len != -1) {
                    os.write(buffer, 0, len)
                }
            } while (len != -1)
            os.flush()

//            val dstFile = File(filePath)
            if (dstFile.exists()) {//插件 apk 文件已经存在
                Toast.makeText(this, "dex overwrite" + dstFile.length(), Toast.LENGTH_SHORT).show()
            }

//            PluginManager.loadPath(this) //加载，初始化 resource

            //初始化 插件 applicationInfo
            LoadedApkAppInfo.hookLoadedApkInActivityThread(dstFile)
            apkPath = filePath

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                os?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun openPlugin(view: View) {

//        LoadApkResDir.switchToPlugResources(apkPath)

//        ARouter.getInstance().build("/app/proxy")
//            .withString("className", PluginManager.packageInfo!!.activities[0].name)
//            .navigation()

        val intent = Intent()
        intent.component = ComponentName("com.stone.taopiaopiao", "com.stone.taopiaopiao.ActivityMain")
        startActivity(intent)

        LoadApkResDir.switchToPlugResources(applicationInfo.sourceDir)

    }
}