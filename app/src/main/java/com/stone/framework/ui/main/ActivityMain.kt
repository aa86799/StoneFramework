package com.stone.framework.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.stone.framework.R
import com.stone.framework.frame.BaseActivity
import com.stone.framework.config.ARouterConfig
import com.stone.framework.di.component.DaggerActivityComponent
import leakcanary.LeakSentry


@Route(path = ARouterConfig.ACTIVITY_MAIN)
class ActivityMain : BaseActivity<MainPresenter>(), MainContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        LeakSentry.refWatcher.watch(this)

        DaggerActivityComponent.create()
            .inject(this)

        mPresenter?.getData()

    }

    override fun onDestroy() {
        cancelToastWidget()
        super.onDestroy()
    }
}