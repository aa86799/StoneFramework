package com.stone.module.pet.ui.fragment.home.mine

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.stone.module.pet.R
import com.stone.module.pet.gilde.ImageUtil
import com.stone.module.pet.ui.base.BaseFragment
import com.stone.module.pet.ui.fragment.bottom.BottomItemFragment
import com.stone.module.pet.ui.fragment.userinfo.FragmentUserInfo
import com.stone.lib.common.ui.widget.StarLayout
import com.stone.lib.common.util.ViewInject
import de.hdodenhof.circleimageview.CircleImageView

/**
 * desc:    我的
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 14:52
 */
class FragmentMine : BottomItemFragment<MinePresenter>(), MineContact.View {

    @JvmField
    @ViewInject(R.id.layout_header_top_left_iv)
    var mIvBack: ImageView? = null

    @JvmField
    @ViewInject(R.id.layout_header_module_title_tv)
    var mTvTitle: TextView? = null

    @JvmField
    @ViewInject(R.id.fragment_mine_user_iv)
    var mIvUser: CircleImageView? = null

    @JvmField
    @ViewInject(R.id.fragment_mine_name_tv)
    var mTvName: TextView? = null

    @JvmField
    @ViewInject(R.id.fragment_mine_score_tv)
    var mTvScore: TextView? = null

    @JvmField
    @ViewInject(R.id.fragment_mine_star_sl)
    var starLayout: StarLayout? = null

    @JvmField
    @ViewInject(R.id.fragment_mine_rv)
    var mRv: RecyclerView? = null

    private var mAdapter: SettingAdapter? = null

    override fun onError() {

    }

    override fun getLayoutId(): Int {
        return R.layout.mpt_fragment_mine
    }

    override fun init() {
        mIvBack?.visibility = View.GONE
        mTvTitle?.text = "我的"
        ImageUtil.load(this, "http://oss-qn-fm.9worker.com/4eb534e5-4fd8-4c92-be51-fe112a739056?imageslim", mIvUser)
        mTvName?.text = "张三李四"
        mTvScore?.text = "我的评分: 3.5"
        starLayout?.setScore(3.5f)

        mRv?.layoutManager = LinearLayoutManager(context)
        mRv?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val data = mutableListOf<SettingVM>()
        data.add(SettingVM(R.drawable.mpt_ic_launcher_background, "xx信息", ""))
        data.add(SettingVM(R.drawable.mpt_ic_launcher_background, "修改手机号", "188***7887"))
        data.add(SettingVM(R.drawable.mpt_ic_launcher_background, "aaa", ""))
        data.add(SettingVM(R.drawable.mpt_ic_launcher_background, "金额", "¥6688993.78"))
        data.add(SettingVM(R.drawable.mpt_ic_launcher_background, "bbb", ""))
        data.add(SettingVM(R.drawable.mpt_ic_launcher_background, "ccc", ""))

        mAdapter = SettingAdapter(data)
        mRv?.adapter = mAdapter

        mAdapter?.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            showMsg(position.toString())
            when (position) {
                0 -> {//
                    getParentDelegate<BaseFragment<*>>().start(FragmentUserInfo())
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