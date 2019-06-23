package com.stone.framework.ui.fragment.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.flyco.tablayout.SegmentTabLayout
import com.flyco.tablayout.listener.OnTabSelectListener
import com.stone.framework.R
import com.stone.framework.ui.fragment.bottom.BottomItemFragment
import com.stone.framework.ui.fragment.home.collect.FragmentCollect

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 15:21
 */
class FragmentHome: BottomItemFragment<HomePresenter>(), HomeContact.View {

    override fun onError() {

    }

    @JvmField
    @BindView(R.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    @JvmField
    @BindView(R.id.layout_header_top_left_iv)
    var mBack: ImageView? = null

    @JvmField
    @BindView(R.id.fragment_main_tab)
    var mTabLayout: SegmentTabLayout? = null

    private var mCurrent: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun init() {
        mTvTitle?.text = "首页"
        mBack?.visibility = View.GONE

        mTabLayout?.setTabData(arrayOf("待取件", "运输中", "已送达"))

        val fragments = mutableListOf(FragmentCollect(0), FragmentCollect(1), FragmentCollect(2))
        mTabLayout?.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                showHideFragment(fragments[position], fragments[mCurrent])
                mCurrent = position
            }

            override fun onTabReselect(position: Int) {

            }

        })

        loadMultipleRootFragment(R.id.fragment_main_fl, 0, *fragments.toTypedArray())

    }
}