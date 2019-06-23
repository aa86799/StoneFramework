package com.stone.framework.ui.fragment.home.collect

import android.view.View
import android.widget.TextView
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
                        showMsg("${mType*10}")
                    }
                    R.id.item_collect_out_order_tv -> {
                        showMsg("${mType*100}")
                    }
                }
            }
    }


}