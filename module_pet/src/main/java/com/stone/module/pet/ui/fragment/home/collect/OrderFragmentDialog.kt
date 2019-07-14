package com.stone.module.pet.ui.fragment.home.collect

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.stone.module.pet.R
import com.stone.module.pet.bean.api.PageBean
import com.stone.module.pet.bean.test.PendingOrderEntity
import com.stone.module.pet.ui.base.BaseDialogFragment

class OrderFragmentDialog : BaseDialogFragment<CollectPresenter>(), CollectContact.View {
    override fun showData(pageBean: PageBean<PendingOrderEntity>) {

    }


    override fun onError() {
    }

    override fun getLayoutId(): Int {
        return R.layout.mpt_layout_popup_in_order
    }

    override fun init() {
        val tvTime: TextView? = mRootView?.findViewById(R.id.layout_popup_in_order_time_tv)
        tvTime?.text = "预约时间: 2019-06-30 18:06:00"
        val tvPetType: TextView? = mRootView?.findViewById(R.id.layout_popup_in_order_pet_type)
        tvPetType?.text = "狗"
        val tvTransType: TextView? = mRootView?.findViewById(R.id.layout_popup_in_order_distance)
        tvTransType?.text = "专车运输"
        val tvAddress: TextView? = mRootView?.findViewById(R.id.layout_popup_in_order_address)
        tvAddress?.text = "江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx"
        val tvAddressSend: TextView? = mRootView?.findViewById(R.id.layout_popup_in_order_address_send)
        tvAddressSend?.text = "江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy"

        mRootView?.findViewById<View>(R.id.layout_popup_in_order_submit)
            ?.setOnClickListener {
                showMsg("接单了")
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  Dialog(context!!, R.style.mpt_MyAppCompatDialogTheme)
    }
}
