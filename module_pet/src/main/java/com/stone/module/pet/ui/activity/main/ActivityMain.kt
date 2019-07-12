package com.stone.module.pet.ui.activity.main

import com.stone.module.pet.ui.fragment.bottom.BottomFragment
import com.stone.module.pet.R
import com.stone.module.pet.di.component.DaggerActivityComponent
import com.stone.module.pet.ui.base.BaseActivity
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