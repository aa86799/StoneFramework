package com.stone.framework.ui.fragment.home.mine

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.blankj.utilcode.util.AppUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.stone.framework.R
import com.stone.framework.config.ARouterConfig
import com.stone.framework.gilde.ImageUtil
import com.stone.framework.ui.fragment.bottom.BottomItemFragment
import com.stone.lib.common.ui.widget.StarLayout
import dagger.internal.Beta
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

/**
 * desc:    我的
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 14:52
 */
class FragmentMine: BottomItemFragment<MinePresenter>(), MineContact.View {

    @JvmField
    @BindView(R.id.layout_header_module_title_tv)
    var mTvTitle:TextView? = null

    @JvmField
    @BindView(R.id.fragment_mine_user_iv)
    var mIvUser:CircleImageView? = null

    @JvmField
    @BindView(R.id.fragment_mine_name_tv)
    var mTvName:TextView? = null

    @JvmField
    @BindView(R.id.fragment_mine_score_tv)
    var mTvScore:TextView? = null

    @JvmField
    @BindView(R.id.fragment_mine_star_sl)
    var starLayout:StarLayout? = null

    @JvmField
    @BindView(R.id.item_setting_version_tv)
    var mTvVersion:TextView? = null

    @JvmField
    @BindView(R.id.fragment_mine_rv)
    var mRv: RecyclerView? = null

    private var mAdapter: SettingAdapter? = null

    override fun onError() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun init() {
        mTvTitle?.text = "我的"
        ImageUtil.load(this, "http://oss-qn-fm.9worker.com/4eb534e5-4fd8-4c92-be51-fe112a739056?imageslim", mIvUser)
        mTvName?.text = "张三李四"
        mTvScore?.text = "我的评分: 3.5"
        starLayout?.setScore(3.5f)
        mTvVersion?.text = "版本1.0"

        mRv?.layoutManager = LinearLayoutManager(context)
        mRv?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val data = mutableListOf<SettingVM>()
        data.add(SettingVM(R.drawable.ic_launcher_background, "司机信息", ""))
        data.add(SettingVM(R.drawable.ic_launcher_background, "修改手机号", "188***7887"))
        data.add(SettingVM(R.drawable.ic_launcher_background, "历史订单", ""))
        data.add(SettingVM(R.drawable.ic_launcher_background, "我的余额", "¥6688993.78"))
        data.add(SettingVM(R.drawable.ic_launcher_background, "系统消息", ""))
        data.add(SettingVM(R.drawable.ic_launcher_background, "任务奖励", ""))

        mAdapter = SettingAdapter(data)
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