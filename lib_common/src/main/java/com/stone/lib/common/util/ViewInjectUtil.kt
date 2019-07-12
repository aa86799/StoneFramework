package com.stone.lib.common.util

import androidx.fragment.app.Fragment
import java.lang.reflect.Method

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-12 15:08
 */
class ViewInjectUtil {
    companion object {
        fun injectViews(component: Any) {
            val clazz = component::class.java
            clazz.declaredFields.forEach {
                val anno = it.getAnnotation(ViewInject::class.java)
                println("stone->$anno.toString()")
                if (anno != null) {
                    val viewId = anno.value
                    var method: Method? = null
                    var view: Any? = null
                    if (component is Fragment) {
                        method = component.view!!::class.java.getMethod("findViewById", Int::class.java)
                        view = method.invoke(component.view, viewId)
                    } else {//activity
                        method = clazz.getMethod("findViewById", Int::class.java)
                        view = method.invoke(component, viewId)
                    }

                    it.isAccessible = true
                    it.set(component, view)
                }

            }

        }
    }
}