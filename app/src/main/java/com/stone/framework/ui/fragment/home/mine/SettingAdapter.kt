package com.stone.framework.ui.fragment.home.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.stone.framework.R

class SettingAdapter(data: List<SettingVM>?) : BaseQuickAdapter<SettingVM, BaseViewHolder>(R.layout.item_setting, data) {

    override fun convert(helper: BaseViewHolder, item: SettingVM) {
        helper.setText(R.id.item_setting_name_tv, item.name)
        helper.setText(R.id.item_setting_right_tv, item.right)
        helper.setImageDrawable(R.id.item_setting_iv, helper.itemView.context.resources.getDrawable(item.icon))
    }
}
