package com.stone.module.pet.ui.fragment.home.collect

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.stone.module.pet.R
import com.stone.module.pet.bean.test.PendingOrderEntity

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-06-23 19:02
 */
class CollectAdapter : BaseQuickAdapter<PendingOrderEntity, BaseViewHolder>(R.layout.mpt_item_collect) {

    override fun convert(helper: BaseViewHolder?, item: PendingOrderEntity?) {
//        helper?.setText(R.id.item_collect_time_tv, "")
//        helper?.setText(R.id.item_collect_type_tv, "")
//        helper?.setText(R.id.item_collect_trans_type_tv, "")
//        helper?.setText(R.id.item_collect_user_tv, "")
//        helper?.setText(R.id.item_collect_phone_tv, "")
//        helper?.setText(R.id.item_collect_address_tv, "")
//        helper?.setText(R.id.item_collect_order_titme_tv, "")

        helper?.addOnClickListener(R.id.item_collect_in_order_tv, R.id.item_collect_out_order_tv)

    }

}