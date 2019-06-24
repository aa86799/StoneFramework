package com.stone.framework.ui.fragment.home.mine

import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView

interface MineContact {

    interface View : BaseView {
        //        void showAdvImg(SplashEntity entity);

        fun onError()
    }

    abstract class Presenter : BasePresenter<View>() {
        internal abstract fun loadAdvData()
    }
}
