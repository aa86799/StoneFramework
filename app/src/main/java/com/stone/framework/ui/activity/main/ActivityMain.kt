package com.stone.framework.ui.activity.main

import com.stone.framework.R
import com.stone.framework.di.component.DaggerActivityComponent
import com.stone.framework.ui.base.BaseActivity
import com.stone.framework.ui.fragment.bottom.BottomFragment
import leakcanary.LeakSentry


class ActivityMain : BaseActivity<MainPresenter>(), MainContract.View {



    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        LeakSentry.refWatcher.watch(this)

        loadRootFragment(R.id.activity_main_fl, BottomFragment())

        DaggerActivityComponent.create()
            .inject(this)

        mPresenter?.getData()

    }

    override fun onDestroy() {
        cancelToastWidget()
        super.onDestroy()
    }
}