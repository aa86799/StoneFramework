package com.stone.framework.ui.fragment.home.collect

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.stone.framework.R
import com.stone.framework.ui.base.BaseDialogFragment

class OrderFragmentDialog : BaseDialogFragment<CollectPresenter>(), CollectContact.View {

    override fun onError() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_popup_in_order
    }

    override fun init() {
        val tvTime: TextView? = mRootView?.findViewById(com.stone.framework.R.id.layout_popup_in_order_time_tv)
        tvTime?.text = "预约时间: 2019-06-30 18:06:00"
        val tvPetType: TextView? = mRootView?.findViewById(com.stone.framework.R.id.layout_popup_in_order_pet_type)
        tvPetType?.text = "狗"
        val tvTransType: TextView? = mRootView?.findViewById(com.stone.framework.R.id.layout_popup_in_order_distance)
        tvTransType?.text = "专车运输"
        val tvAddress: TextView? = mRootView?.findViewById(com.stone.framework.R.id.layout_popup_in_order_address)
        tvAddress?.text = "江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx江苏省xxxx"
        val tvAddressSend: TextView? = mRootView?.findViewById(com.stone.framework.R.id.layout_popup_in_order_address_send)
        tvAddressSend?.text = "江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy江苏省yyyy"

        mRootView?.findViewById<View>(com.stone.framework.R.id.layout_popup_in_order_submit)
            ?.setOnClickListener {
                showMsg("接单了")
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  Dialog(context!!, R.style.MyAppCompatDialogTheme)
    }
}
