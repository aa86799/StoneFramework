package com.stone.module.pet.ui.fragment.home.collect

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.stone.module.pet.bean.api.PageBean
import com.stone.module.pet.bean.test.PendingOrderEntity
import com.stone.module.pet.ui.base.BaseFragment
import com.stone.lib.common.util.ViewInject


/**
 * desc:    待收件
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 18:41
 */
class FragmentCollect(val mType: String) : BaseFragment<CollectPresenter>(), CollectContact.View {

    @JvmField
    @ViewInject(com.stone.module.pet.R.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    @JvmField
    @ViewInject(com.stone.module.pet.R.id.fragment_collect_srl)
    var mRefreshLayout: RefreshLayout? = null

    @JvmField
    @ViewInject(com.stone.module.pet.R.id.fragment_collect_rv)
    var mRv: RecyclerView? = null

    private val mAdapter: CollectAdapter = CollectAdapter()

    private var mCurrentPage = 1

    override fun onError() {

    }

    override fun getLayoutId(): Int {
        return com.stone.module.pet.R.layout.mpt_fragment_collect
    }

    override fun init() {
        mRv?.layoutManager = LinearLayoutManager(context)
        mRv?.adapter = mAdapter

        mAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                showMsg(mType.toString())
            }

        mAdapter.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, i: Int ->
                when (view.id) {
                    com.stone.module.pet.R.id.item_collect_in_order_tv -> {
                        inOrder()
                    }
                    com.stone.module.pet.R.id.item_collect_out_order_tv -> {
                        showMsg("${33 * 100}")
                    }
                }
            }

        mRefreshLayout?.setRefreshHeader(ClassicsHeader(context))
        mRefreshLayout?.setRefreshFooter(ClassicsFooter(context))

        mRefreshLayout?.setOnRefreshListener { refreshLayout ->
            refreshLayout.resetNoMoreData()
            //如下内部赋值 语法错误
//            mPresenter?.loadData(mType, mCurrentPage = 1)

            //如下，有没有run{}都可以，分成两部分了
            run { mCurrentPage = 1; mPresenter?.loadData(mType, mCurrentPage) }

        }

        mRefreshLayout?.setOnLoadMoreListener{refreshLayout->
            //可以的 ++表达式可以
            mPresenter?.loadData(mType, ++mCurrentPage)
        }

//        mPresenter?.loadData(mType, mCurrentPage)
    }

    private fun inOrder() {//接单
        val dialog = OrderFragmentDialog()
        dialog.show(activity?.supportFragmentManager, "order-dialog")
    }

    override fun showData(pageBean: PageBean<PendingOrderEntity>) {
//        pageBean == null ?: return //?: 左边表达式成立 才计算右边的；  下面的 if{}等价。只是这里pageBean 是一个非空类型，所以不需要这个判断
//        if (pageBean == null) {
//            return
//        }

        when (mCurrentPage) {
            1 -> {
                mAdapter.setNewData(pageBean.records)
                if (pageBean.records?.size!! < pageBean.size) {
                    //mRefreshLayout.finishRefreshWithNoMoreData()
                    mRefreshLayout?.finishRefresh(500, true, true)
                } else {
                    mRefreshLayout?.finishRefresh(500)
                }
            }
            else -> {
                if (pageBean.records?.isEmpty()!!) {
                    mCurrentPage--
                }
                mAdapter.addData(pageBean.records!!)
                if (pageBean.records?.size!! < pageBean.size) {
                    mRefreshLayout?.finishLoadMore(500, true, true)
                    mRefreshLayout?.finishLoadMoreWithNoMoreData()
                } else {
                    mRefreshLayout?.finishLoadMore(500)
                }
            }
        }

        mAdapter.addData(PendingOrderEntity())
        mAdapter.addData(PendingOrderEntity())
    }


//    private fun inOrder() {//接单
//        // 用于PopupWindow的View
//        val contentView = LayoutInflater.from(context).inflate(R.layout.mpt_layout_popup_in_order, null, false)
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