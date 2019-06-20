package com.stone.framework.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.stone.framework.R
import com.stone.framework.base.BaseActivity
import com.stone.framework.config.ARouterConfig
import com.stone.framework.di.component.DaggerActivityComponent
import leakcanary.LeakSentry


@Route(path = ARouterConfig.ACTIVITY_MAIN)
class MainActivity : BaseActivity<MainPresenter>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main //To change body of created functions use File | Settings | File Templates.
    }

    override fun init() {
        LeakSentry.refWatcher.watch(this)

        DaggerActivityComponent.create()
            .inject(this) //To change body of created functions use File | Settings | File Templates.
    }

}