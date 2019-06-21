package com.stone.framework.frame.mvp

import android.content.Context
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> {

    protected var mContext: Context? = null
    private var mViewRef: WeakReference<V>? = null
    var mView: V? = null

    fun attachView(view: BaseView) {
        mViewRef = WeakReference(view as V)
        mView = mViewRef?.get()
    }

    fun detachView() {
        mView = null
    }

    fun setContext(context: Context) {
        mContext = context
    }
}