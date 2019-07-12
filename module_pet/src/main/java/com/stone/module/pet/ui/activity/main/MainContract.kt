package com.stone.module.pet.ui.activity.main

import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView

interface  MainContract {
    interface View: BaseView {

    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getData()
    }
}
