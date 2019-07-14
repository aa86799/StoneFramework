package com.stone.module.pet.ui.fragment.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.flyco.tablayout.SegmentTabLayout
import com.flyco.tablayout.listener.OnTabSelectListener
import com.stone.module.pet.R
import com.stone.module.pet.R2
import com.stone.module.pet.ui.fragment.bottom.BottomItemFragment
import com.stone.module.pet.ui.fragment.home.collect.FragmentCollect

/**
 * desc:    首页
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 15:21
 */
class FragmentHome : BottomItemFragment<HomePresenter>(), HomeContact.View {

    override fun onError() {

    }

    @JvmField
    @BindView(R2.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    @JvmField
    @BindView(R2.id.layout_header_top_left_iv)
    var mBack: ImageView? = null

    @JvmField
    @BindView(R2.id.fragment_main_tab)
    var mTabLayout: SegmentTabLayout? = null

    private var mCurrent: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.mpt_fragment_home
    }

    override fun init() {
        mTvTitle?.text = "首页"
        mBack?.visibility = View.GONE

        mTabLayout?.setTabData(arrayOf("Tab1", "Tab2", "Tab3"))

        val fragments = mutableListOf(FragmentCollect("0"), FragmentCollect("1"), FragmentCollect("2"))
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