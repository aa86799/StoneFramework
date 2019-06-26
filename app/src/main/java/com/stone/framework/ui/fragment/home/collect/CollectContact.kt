package com.stone.framework.ui.fragment.home.collect

import com.stone.framework.bean.PendingOrderEntity
import com.stone.framework.bean.api.PageBean
import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView

interface CollectContact {

    interface View : BaseView {
        fun showData(pageBean: PageBean<PendingOrderEntity>)

        fun onError()
    }

    abstract class Presenter : BasePresenter<View>() {
        internal abstract fun loadData(token: String, currentPage: Int)
    }
}
