package com.stone.module.pet.ui.fragment.userinfo

import android.view.Gravity
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.stone.module.pet.R

class UserInfoAdapter(data: List<UserInfoVM>?) : BaseQuickAdapter<UserInfoVM, BaseViewHolder>(R.layout.mpt_item_user_info, data) {

    override fun convert(helper: BaseViewHolder, item: UserInfoVM) {
        helper.setText(R.id.item_user_info_name_tv, item.name)

        val tv: TextView = helper.getView(R.id.item_user_info_right_tv)
        tv.text = item.right
        if (helper.adapterPosition == mData.size - 2 ||  helper.adapterPosition == mData.size - 3) {
            tv.gravity = Gravity.CENTER_VERTICAL
        }

        if (helper.adapterPosition == mData.size - 1) {
            val tv: TextView = helper.getView(R.id.item_user_info_right_tv)
            tv.setTextColor(mContext.resources.getColor(R.color.mpt_colorAccent))
        }
    }
}
