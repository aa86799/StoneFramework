package com.stone.framework.ui.fragment.home.collect

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.stone.framework.R
import com.stone.framework.ui.base.BaseFragment

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 18:41
 */
class FragmentCollect(val mType: Int) : BaseFragment<CollectPresenter>(), CollectContact.View {


    @JvmField
    @BindView(R.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    @JvmField
    @BindView(R.id.fragment_collect_srl)
    var mRefreshLayout: RefreshLayout? = null

    @JvmField
    @BindView(R.id.fragment_collect_rv)
    var mRv: RecyclerView? = null

    private val mAdapter: CollectAdapter = CollectAdapter()

    override fun onError() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_collect
    }

    override fun init() {
        mRv?.layoutManager = LinearLayoutManager(context)
        mRv?.adapter = mAdapter

        mAdapter.addData("")
        mAdapter.addData("")
        mAdapter.addData("")
        mAdapter.addData("")
        mAdapter.addData("")

//        mPresenter.loadAdvData(mType)

        mAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                showMsg(mType.toString())
            }

        mAdapter.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, i: Int ->
                when (view.id) {
                    R.id.item_collect_in_order_tv -> {
                        inOrder()
                    }
                    R.id.item_collect_out_order_tv -> {
                        showMsg("${mType * 100}")
                    }
                }
            }
    }

    private fun inOrder() {//接单
        val dialog = OrderFragmentDialog()
        dialog.show(activity?.supportFragmentManager, "order-dialog")
    }

//    private fun inOrder() {//接单
//        // 用于PopupWindow的View
//        val contentView = LayoutInflater.from(context).inflate(R.layout.layout_popup_in_order, null, false)
//        // 创建PopupWindow对象，其中：
//        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
//        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
//        val window =
//            PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true)
//
//        /* xxxxx */
//        val tvTime:TextView = contentView.findViewById(R.id.layout_popup_in_order_time_tv)
//        tvTime.text = "预约时间: 2019-06-30 18:06:00"
//        val tvPetType:TextView = contentView.findViewById(R.id.layout_popup_in_order_pet_type)
//        tvPetType.text = "狗"
//        val tvTransType:TextView = contentView.findViewById(R.id.layout_popup_in_order_distance)
//        tvTransType.text = "专车运输"
//        val tvAddress:TextView = contentView.findViewById(R.id.layout_popup_in_order_address)
//        tvAddress.text = "江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx"
//        val tvAddressSend:TextView = contentView.findViewById(R.id.layout_popup_in_order_address_send)
//        tvAddressSend.text = "江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy"
//
//        contentView.findViewById<View>(R.id.layout_popup_in_order_submit)
//            .setOnClickListener {
//                showMsg("接单了")
//            }
//        /* xxxxx */
//
//        window.isClippingEnabled = false //不绘制到沉浸状态栏
//        // 设置PopupWindow的背景
//        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        // 设置PopupWindow是否能响应外部点击事件
//        window.isOutsideTouchable = true
//        // 设置PopupWindow是否能响应点击事件
//        window.isTouchable = true
//        // 显示PopupWindow，其中：
//        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
////        window.showAsDropDown(anchor, xoff, yoff);
//        // 或者也可以调用此方法显示PopupWindow，其中：
//        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
//        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
//        window.showAtLocation(mRootView?.findViewById(R.id.fragment_collect_parent), Gravity.CENTER, 300, 3000)
//    }
}