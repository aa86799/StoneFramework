package com.stone.lib.common.router

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import com.stone.lib.annotations.router.entity.RouteMeta
import com.stone.lib.common.router.template.IRouteGroup
import com.stone.lib.common.router.template.IRouteRoot
import com.stone.lib.common.router.template.IService
import com.stone.lib.common.router.utils.ClassUtils
import android.content.Intent
import androidx.core.app.ActivityCompat

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 00:11
 */
object SRouter {
    private val PACKAGE_OF_GENERATE_FILE = "com.stone.router"
    private val SDK_NAME = "SRouter"
    private val SEPARATOR = "$$"
    private val SUFFIX_ROOT = "Root"

    private var handler: Handler? = null
    private var mContext: Application? = null

    init {
        handler = Handler(Looper.getMainLooper())
    }

    fun init(application: Application) {
        this.mContext = application
        loadInfo()
    }

    /**
     * 分组表制作
     */
    private fun loadInfo() {
        //获得所有 apt 生成的路由类的全类名 (路由表)
        val routeSet = ClassUtils.getFileNameByPackageName(mContext!!, PACKAGE_OF_GENERATE_FILE)

        for (className in routeSet) {
            if (className.startsWith("$PACKAGE_OF_GENERATE_FILE.$SDK_NAME$SEPARATOR$SUFFIX_ROOT")) {
                // root中注册的是分组信息 加入仓库中
                ((Class.forName(className).getConstructor().newInstance()) as IRouteRoot).loadInto(Warehouse.groupsIndex)
            }
        }

        Warehouse.groupsIndex.entries.forEach {
            Log.e("stone->", "Root映射表[ " + it.key + " : " + it.value + "]")
        }
    }

    fun build(path: String): Postcard {
        if (TextUtils.isEmpty(path)) {
            throw RuntimeException("路由地址无效!")
        } else {
            return build(path, extractGroup(path))
        }
    }

    fun build(path: String, group: String?): Postcard {
        return if (TextUtils.isEmpty(path) || TextUtils.isEmpty(group)) {
            throw RuntimeException("路由地址无效!")
        } else {
            Postcard(path, group!!)
        }
    }

    /**
     * 获得组别
     *
     * @param path
     * @return
     */
    private fun extractGroup(path: String): String? {
        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
            throw RuntimeException("$path : 不能提取group.")
        }
        return try {
            val defaultGroup = path.substring(1, path.indexOf("/", 1))
            if (TextUtils.isEmpty(defaultGroup)) {
                throw RuntimeException("$path : 不能提取group.")
            } else {
                defaultGroup //return
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null //return
        }

    }

    fun navigation(context: Context?, postcard: Postcard?, requestCode: Int?, callback: NavigationCallback?): Any? {
        try {
            prepareCard(postcard!!)
        } catch (e: Exception) {
            e.printStackTrace()
            //没找到
            callback?.onLost(postcard!!)
            return null
        }

        callback?.onFound(postcard)

        when (postcard.type) {
            RouteMeta.Type.ACTIVITY -> {
                val curContext = context ?: mContext
                val intent = Intent(curContext, postcard.destination)
                if (postcard.extras != null) {
                    intent.putExtras(postcard.extras!!)
                }
                val flags = postcard.flags
                if (-1 != flags) {
                    intent.flags = flags
                } else if (curContext !is Activity) {
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                }
                handler?.post {
                    //可能需要返回码
                    if (requestCode!! > 0) {
                        ActivityCompat.startActivityForResult(
                            curContext as Activity, intent, requestCode, postcard.optionsBundle
                        )
                    } else {
                        ActivityCompat.startActivity(curContext!!, intent, postcard.optionsBundle)
                    }

                    if ((0 != postcard.enterAnim || 0 != postcard.exitAnim) && curContext is Activity) {
                        //老版本
                        curContext.overridePendingTransition(postcard.enterAnim, postcard.exitAnim)
                    }
                    //跳转完成
                    callback?.onArrival(postcard)
                }
                return null
            }
            RouteMeta.Type.ISERVICE -> {
                return postcard.service
            }
            else -> {
                return null
            }
        }
    }

    private fun prepareCard(card: Postcard) {
        val routeMeta = Warehouse.routes[card.path]
        //还没准备的
        if (null == routeMeta) {
            //创建并调用 loadInto 函数,然后记录在仓库
            val groupMeta =
                Warehouse.groupsIndex[card.group] ?: throw RuntimeException("没找到对应路由: " + card.group + " " + card.path)
            val iGroupInstance: IRouteGroup
            try {
                iGroupInstance = groupMeta.getConstructor().newInstance()
            } catch (e: Exception) {
                throw RuntimeException("路由分组映射表记录失败.", e)
            }

            iGroupInstance.loadInto(Warehouse.routes)
            //已经准备过了就可以移除了 (不会一直存在内存中)
            Warehouse.groupsIndex.remove(card.group)
            //再次进入 else
            prepareCard(card)
        } else {
            //类 要跳转的activity 或IService实现类
            card.destination = routeMeta.destination
            card.type = routeMeta.type
            when (routeMeta.type) {
                RouteMeta.Type.ISERVICE -> {
                    val destination = routeMeta.destination
                    var service: IService? = Warehouse.services[destination]
                    if (null == service) {
                        try {
                            service = destination.getConstructor().newInstance() as IService
                            Warehouse.services.put(destination, service)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                    card.service = service
                }
                else -> {
                }
            }
        }
    }


}
