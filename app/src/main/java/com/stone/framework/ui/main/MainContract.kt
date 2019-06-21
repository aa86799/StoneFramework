package com.stone.framework.ui.main

import com.stone.framework.frame.mvp.BasePresenter
import com.stone.framework.frame.mvp.BaseView

interface  MainContract {
    interface View: BaseView {

    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getData()
    }
}
