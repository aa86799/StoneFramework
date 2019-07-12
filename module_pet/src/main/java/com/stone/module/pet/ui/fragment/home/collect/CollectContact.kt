package com.stone.module.pet.ui.fragment.home.collect

import com.stone.module.pet.bean.api.PageBean
import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView
import com.stone.module.pet.bean.test.PendingOrderEntity

interface CollectContact {

    interface View : BaseView {
        fun showData(pageBean: PageBean<PendingOrderEntity>)

        fun onError()
    }

    abstract class Presenter : BasePresenter<View>() {
        internal abstract fun loadData(token: String, currentPage: Int)
    }
}
