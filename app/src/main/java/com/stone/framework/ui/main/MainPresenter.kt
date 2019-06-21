package com.stone.framework.ui.main

import com.stone.framework.App
import com.stone.framework.R
import com.stone.framework.bean.HomeEntity
import com.stone.framework.config.api.ApiHelper
import com.stone.framework.util.RxJavaUtil

class MainPresenter: MainContract.Presenter() {

    override fun getData() {
        ApiHelper.service()
            .getHomeIndex()
            .compose(RxJavaUtil.schLife(mView))
            .compose(RxJavaUtil.dataObj<List<HomeEntity>>())//这个java调用时反过来的，泛型在后面
            .subscribe({ result ->
                if (result.isSuccess) {
//                   mView?.showMsg(result.data.size.toString())
                    mView?.showMsg(App.getInstance()?.getString(R.string.system_error)!!)
                }
            }, { error ->
                error.printStackTrace()
                mView?.showMsg(App.getInstance()?.getString(R.string.system_error)!!)
            });

    }


}