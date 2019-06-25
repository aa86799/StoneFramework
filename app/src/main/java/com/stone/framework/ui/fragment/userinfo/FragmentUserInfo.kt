package com.stone.framework.ui.fragment.userinfo

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.stone.framework.R
import com.stone.framework.gilde.ImageUtil
import com.stone.framework.ui.base.BaseFragment
import de.hdodenhof.circleimageview.CircleImageView

class FragmentUserInfo : BaseFragment<UserInfoPresenter>(), UserInfoContact.View {

    @JvmField
    @BindView(R.id.layout_header_top_left_iv)
    var mIvBack: ImageView? = null

    @JvmField
    @BindView(R.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    @JvmField
    @BindView(R.id.fragment_user_info_user_iv)
    var mIvUser: CircleImageView? = null

    @JvmField
    @BindView(R.id.fragment_user_info_name_tv)
    var mTvName: TextView? = null

    @JvmField
    @BindView(R.id.fragment_user_info_phone_tv)
    var mTvPhone: TextView? = null

    @JvmField
    @BindView(R.id.fragment_user_info_car_tv)
    var mTvCar: TextView? = null

    @JvmField
    @BindView(R.id.item_setting_see_tv)
    var mTvSee: TextView? = null

    @JvmField
    @BindView(R.id.fragment_user_info_rv)
    var mRv: RecyclerView? = null

    private var mAdapter: UserInfoAdapter? = null


    override fun getLayoutId(): Int {
        return R.layout.fragment_user_info
    }

    override fun onError() {

    }

    override fun init() {
        mIvBack?.setOnClickListener {
            pop()
        }
        mTvTitle?.text = "司机信息"
        ImageUtil.load(this, "http://oss-qn-fm.9worker.com/4eb534e5-4fd8-4c92-be51-fe112a739056?imageslim", mIvUser)
        mTvName?.text = "张三李四"
        mTvPhone?.text = "15812345678"
        mTvCar?.text = "粤AXY123"

        initRv()

        mTvSee?.setOnClickListener {
            showMsg("查看证件信息")
        }
    }

    private fun initRv() {
        mRv?.layoutManager = LinearLayoutManager(context)
        mRv?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val data = mutableListOf<UserInfoVM>()
        data.add(UserInfoVM("驾驶证号码", "55667788991100"))
        data.add(UserInfoVM("出生年月", "1993-03-21"))
        data.add(UserInfoVM("性别", "男"))
        data.add(UserInfoVM("所在地", "江苏省xxxxxxx江苏省xxxxxxx江苏省xxxxxxx江苏省xxxxxxx江苏省xxxxxxx江苏省xxxxxxx江苏省xxxxxxx"))
        data.add(UserInfoVM("详细地址", "江苏省yyyyyyy江苏省yyyyyyy江苏省yyyyyyy江苏省yyyyyyy江苏省yyyyyyy江苏省yyyyyyy江苏省yyyyyyy江苏省yyyyyyy"))
        data.add(UserInfoVM("入驻押金", "¥5000"))

        mAdapter = UserInfoAdapter(data)
        mRv?.adapter = mAdapter

        mAdapter?.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            showMsg(position.toString())
            when (position) {
                0 -> {//

                }
                1 -> {//
                }
                2 -> {//
                }
                3 -> {//

                }
                4 -> {//
                }
                5 -> {//
                }
            }
        })
    }

}