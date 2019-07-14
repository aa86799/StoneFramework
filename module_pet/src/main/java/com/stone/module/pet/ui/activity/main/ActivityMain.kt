package com.stone.module.pet.ui.activity.main

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.stone.module.pet.ui.fragment.bottom.BottomFragment
import com.stone.module.pet.R
import com.stone.module.pet.bean.test.User
import com.stone.module.pet.config.ARouterConfig
import com.stone.module.pet.di.component.DaggerActivityComponent
import com.stone.module.pet.ui.base.BaseActivity

@Route(path = ARouterConfig.ACTIVITY_MAIN)
class ActivityMain : BaseActivity<MainPresenter>(), MainContract.View {

    @JvmField
    @Autowired
    var testKey: Int = -1

    @JvmField
    @Autowired
    var customObj: User? = null

    override fun getLayoutId(): Int {
        return R.layout.mpt_activity_main
    }

    override fun init() {
        ARouter.getInstance().inject(this)

//        LeakSentry.refWatcher.watch(this)

        loadRootFragment(R.id.activity_main_fl, BottomFragment())

        DaggerActivityComponent.create()
            .inject(this)

        mPresenter?.getData()

        println("注入参数：$testKey ${(customObj as User).name}")
    }

    override fun onDestroy() {
        cancelToastWidget()
        super.onDestroy()
    }
}