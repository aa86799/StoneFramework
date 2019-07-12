package com.stone.module.pet.ui.fragment.home.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.stone.module.pet.R

class SettingAdapter(data: List<SettingVM>?) : BaseQuickAdapter<SettingVM, BaseViewHolder>(R.layout.item_setting, data) {

    override fun convert(helper: BaseViewHolder, item: SettingVM) {
        helper.setText(R.id.item_setting_name_tv, item.name)
        helper.setText(R.id.item_setting_right_tv, item.right)
//        helper.setImageDrawable(R.id.item_setting_iv, helper.itemView.context.resources.getDrawable(item.icon))
        helper.setBackgroundColor(R.id.item_setting_iv, mContext.resources.getColor(R.color.colorPrimary))
    }
}
