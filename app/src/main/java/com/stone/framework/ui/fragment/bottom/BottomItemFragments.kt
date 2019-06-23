package com.stone.framework.ui.fragment.bottom

import android.os.SystemClock
import com.blankj.utilcode.util.ToastUtils
import com.stone.framework.ui.base.BaseFragment
import com.stone.framework.ui.fragment.home.HomeContact
import com.stone.framework.ui.fragment.home.HomePresenter

/**
 * desc     : 底部菜单项
 * author   : stone
 *
 * email    : aa86799@163.com
 * time     : 2018/7/10 15 09
 */
abstract class BottomItemFragments : BaseFragment<HomePresenter>(), HomeContact.View {

    private var mExitTime: Long = 0
    private val EXIT_TIME = 2000

    override fun onResume() {
        super.onResume()
        val rootView = view
        if (rootView != null) {
            rootView.isFocusableInTouchMode = true
            rootView.requestFocus()

            //监听 back 键，不再使用 OnKeyListener，改用 onBackPressedSupport()
            //            rootView.setOnKeyListener(this)
        }
    }

    override fun onBackPressedSupport(): Boolean {
        val time = SystemClock.uptimeMillis()
        if (time - mExitTime > EXIT_TIME) {
            mExitTime = time
            ToastUtils.showShort("双击退出")
        } else {
            activity?.finish()

        }
        return true
    }

}
