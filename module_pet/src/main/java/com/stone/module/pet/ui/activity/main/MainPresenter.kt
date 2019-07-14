package com.stone.module.pet.ui.activity.main

import com.alibaba.fastjson.JSON
import com.stone.module.pet.R
import com.stone.module.pet.api.ApiHelper
import com.stone.module.pet.bean.api.PageBean
import com.stone.module.pet.bean.test.HomeBean
import com.stone.module.pet.config.PetGlobalConfig
import com.stone.module.pet.util.RxJavaUtil

class MainPresenter : MainContract.Presenter() {

    override fun getData() {
        ApiHelper.service()
            .getHomeIndex()
            .compose(RxJavaUtil.schLife(mView))
            .compose(RxJavaUtil.dataObj<PageBean<HomeBean>>())//这个与java调用时反过来的，泛型在后面
            .subscribe({ result ->
                if (result.success) {
//                   mView?.showMsg(result.data?.size.toString())
                    mView?.showMsg(JSON.toJSONString(result.data!!))
                    for (i in 0..result.data?.size!!) {
                        val entity: HomeBean = result.data?.records?.get(i)!!
                        println("stone->${entity.toString()}")
                    }

                }
            }, { error ->
                error.printStackTrace()
                mView?.showMsg(PetGlobalConfig.app?.getString(R.string.mpt_system_error)!!)
            })

    }


}