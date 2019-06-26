package com.stone.framework.ui.fragment.home.collect


import com.stone.framework.App
import com.stone.framework.R
import com.stone.framework.bean.PendingOrderEntity
import com.stone.framework.bean.api.PageBean
import com.stone.framework.config.api.ApiHelper
import com.stone.framework.util.RxJavaUtil

class CollectPresenter : CollectContact.Presenter() {

    internal override fun loadData(token: String, currentPage: Int) {
        ApiHelper.service()
            .getDriverDetail(token, currentPage)
            .compose(RxJavaUtil.schLife(mView))
            .compose(RxJavaUtil.dataObj<PageBean<PendingOrderEntity>>())//这个java调用时反过来的，泛型在后面
            .subscribe({ result ->
                if (result.success) {
//                    mView?.showMsg(result.data?.size.toString())
                    mView?.showData(result.data!!)
                }
            }, { error ->
                error.printStackTrace()
                mView?.showMsg(App.getInstance()?.getString(R.string.system_error)!!)
            });
    }
}
