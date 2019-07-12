package com.stone.module.pet.ui.fragment.bottom

import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView

interface  BottomContract {
    interface View: BaseView {

    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getData()
    }
}
