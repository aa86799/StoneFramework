package com.stone.framework.base

import android.content.Context

abstract class BasePresenter<V:BaseView> {

    protected var mView: V? = null
    protected var mContext: Context? = null


    fun setView(v: V) {
        mView = v
    }

    fun setContext(context: Context) {
        mContext = context
    }
}