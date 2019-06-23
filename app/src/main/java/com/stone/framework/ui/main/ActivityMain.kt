package com.stone.framework.ui.main

import android.widget.TextView
import butterknife.BindView
import com.stone.framework.R
import com.stone.framework.di.component.DaggerActivityComponent
import com.stone.framework.frame.BaseActivity
import leakcanary.LeakSentry


class ActivityMain : BaseActivity<MainPresenter>(), MainContract.View {

    @JvmField
    @BindView(R.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        LeakSentry.refWatcher.watch(this)

        mTvTitle?.text = "首页"

        DaggerActivityComponent.create()
            .inject(this)

        mPresenter?.getData()

    }

    override fun onDestroy() {
        cancelToastWidget()
        super.onDestroy()
    }
}