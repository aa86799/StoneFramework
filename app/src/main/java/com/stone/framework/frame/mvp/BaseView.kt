package com.stone.framework.frame.mvp

interface BaseView {

    fun showMsg(msg: String?)

    fun showMsg(msgResId: Int)

}