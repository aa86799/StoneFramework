package com.stone.lib.common.router

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import com.stone.lib.annotations.router.Route
import com.stone.lib.annotations.router.entity.RouteMeta
import com.stone.lib.common.router.template.IService
import javax.lang.model.element.Element
import androidx.core.app.ActivityOptionsCompat
import android.os.Parcelable










/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 05:31
 */
class Postcard constructor(type: Type?, route: Route?, element: Element?) :
    RouteMeta(type, route, element) {

    private var bundle: Bundle? = null
    private var flags = -1

    /**
     * 动画
     */
    //新版 md风格
    private var optionsCompat: Bundle? = null
    //老版
    private var enterAnim: Int = 0
    private var exitAnim: Int = 0

    // 服务
    var service: IService? = null

    constructor(path: String, group: String) : this(null, null, null) {
        this.path = path
        this.group = group
    }

    constructor(path: String, group: String, bundle:Bundle) : this(path, group) {
        this.bundle = bundle
    }

    fun getExtras(): Bundle {
        return bundle!!
    }

    fun getEnterAnim(): Int {
        return enterAnim
    }

    fun getExitAnim(): Int {
        return exitAnim
    }

    /**
     * Intent.FLAG_ACTIVITY**
     *
     * @param flag
     * @return
     */
    fun withFlags(flag: Int): Postcard {
        this.flags = flag
        return this
    }

    fun getFlags(): Int {
        return flags
    }

    /**
     * 跳转动画
     *
     * @param enterAnim
     * @param exitAnim
     * @return
     */
    fun withTransition(enterAnim: Int, exitAnim: Int): Postcard {
        this.enterAnim = enterAnim
        this.exitAnim = exitAnim
        return this
    }

    /**
     * 转场动画
     *
     * @param compat
     * @return
     */
    fun withOptionsCompat(compat: ActivityOptionsCompat?): Postcard {
        if (null != compat) {
            this.optionsCompat = compat.toBundle()
        }
        return this
    }

    fun withString(@Nullable key: String, @Nullable value: String): Postcard {
        bundle?.putString(key, value)
        return this
    }

    fun withBoolean(@Nullable key: String, value: Boolean): Postcard {
        bundle?.putBoolean(key, value)
        return this
    }

    fun withShort(key: String?, value: Short): Postcard {
        bundle?.putShort(key, value)
        return this
    }


    fun withInt(key: String?, value: Int): Postcard {
        bundle?.putInt(key, value)
        return this
    }


    fun withLong(key: String?, value: Long): Postcard {
        bundle?.putLong(key, value)
        return this
    }


    fun withDouble(key: String?, value: Double): Postcard {
        bundle?.putDouble(key, value)
        return this
    }


    fun withByte(key: String?, value: Byte): Postcard {
        bundle?.putByte(key, value)
        return this
    }


    fun withChar(key: String?, value: Char): Postcard {
        bundle?.putChar(key, value)
        return this
    }


    fun withFloat(key: String?, value: Float): Postcard {
        bundle?.putFloat(key, value)
        return this
    }


    fun withParcelable(key: String?, value: Parcelable?): Postcard {
        bundle?.putParcelable(key, value)
        return this
    }


    fun withStringArray(key: String?, value: Array<String>?): Postcard {
        bundle?.putStringArray(key, value)
        return this
    }


    fun withBooleanArray(key: String?, value: BooleanArray): Postcard {
        bundle?.putBooleanArray(key, value)
        return this
    }


    fun withShortArray(key: String?, value: ShortArray): Postcard {
        bundle?.putShortArray(key, value)
        return this
    }


    fun withIntArray(key: String?, value: IntArray): Postcard {
        bundle?.putIntArray(key, value)
        return this
    }


    fun withLongArray(key: String?, value: LongArray): Postcard {
        bundle?.putLongArray(key, value)
        return this
    }


    fun withDoubleArray(key: String?, value: DoubleArray): Postcard {
        bundle?.putDoubleArray(key, value)
        return this
    }


    fun withByteArray(key: String?, value: ByteArray): Postcard {
        bundle?.putByteArray(key, value)
        return this
    }


    fun withCharArray(key: String?, value: CharArray): Postcard {
        bundle?.putCharArray(key, value)
        return this
    }


    fun withFloatArray(key: String?, value: FloatArray): Postcard {
        bundle?.putFloatArray(key, value)
        return this
    }


    fun withParcelableArray(key: String?, value: Array<Parcelable>?): Postcard {
        bundle?.putParcelableArray(key, value)
        return this
    }

    fun withParcelableArrayList(key: String?, value: ArrayList<out Parcelable>?): Postcard {
        bundle?.putParcelableArrayList(key, value)
        return this
    }

    fun withIntegerArrayList(key: String?, value: ArrayList<Int>?): Postcard {
        bundle?.putIntegerArrayList(key, value)
        return this
    }

    fun withStringArrayList(key: String?, value: ArrayList<String>?): Postcard {
        bundle?.putStringArrayList(key, value)
        return this
    }

    fun getOptionsBundle(): Bundle {
        return optionsCompat!!
    }


    fun navigation(): Any? {
        return SRouter.navigation(null, this, -1, null)
    }

    fun navigation(context: Context): Any? {
        return SRouter.navigation(context, this, -1, null)
    }


    fun navigation(context: Context, callback: NavigationCallback): Any? {
        return SRouter.navigation(context, this, -1, callback)
    }

    fun navigation(context: Context, requestCode: Int): Any? {
        return SRouter.navigation(context, this, requestCode, null)
    }

    fun navigation(context: Context, requestCode: Int, callback: NavigationCallback): Any? {
        return SRouter.navigation(context, this, requestCode, callback)
    }

}