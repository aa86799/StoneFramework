package com.stone.module.pet.ui.fragment.home.collect


import com.stone.module.pet.App
import com.stone.module.pet.R
import com.stone.module.pet.bean.api.PageBean
import com.stone.module.pet.api.ApiHelper
import com.stone.module.pet.bean.test.PendingOrderEntity
import com.stone.module.pet.util.RxJavaUtil

class CollectPresenter : CollectContact.Presenter() {

    internal override fun loadData(token: String, currentPage: Int) {
//        ApiHelper.service()
//            .getDriverDetail(token, currentPage)
//            .compose(RxJavaUtil.schLife(mView))
//            .compose(RxJavaUtil.dataObj<PageBean<PendingOrderEntity>>())//这个java调用时反过来的，泛型在后面
//            .subscribe({ result ->
//                if (result.success) {
////                    mView?.showMsg(result.data?.size.toString())
//                    mView?.showData(result.data!!)
//                }
//            }, { error ->
//                error.printStackTrace()
//                mView?.showMsg(App.getInstance()?.getString(R.string.system_error)!!)
//            });
    }
}
