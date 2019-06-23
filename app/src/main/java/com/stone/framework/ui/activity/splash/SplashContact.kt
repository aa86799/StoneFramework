package com.stone.framework.ui.activity.splash


import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView

interface SplashContact {

    interface View : BaseView {
        //        void showAdvImg(SplashEntity entity);

        fun onError()
    }

    abstract class Presenter : BasePresenter<View>() {
        internal abstract fun loadAdvData()
    }
}
