package com.stone.framework.base

import com.blankj.utilcode.util.ToastUtils
import java.lang.reflect.ParameterizedType

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/6/20 18:28
 */
abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseAppCompatActivity(), BaseView {

    protected var mPresenter: P? = null


    @Suppress("UNCHECKED_CAST")
    override fun initPresenter() {
        if (this::class.java.genericSuperclass is ParameterizedType
            && (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments.isNotEmpty()) {

            try {
                val mPresenterClass =
                    (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
                mPresenter = mPresenterClass.newInstance() as P
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            mPresenter?.setView(this)
            mPresenter?.setContext(this)
        }
    }

    override fun showMsg(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun showMsg(msgResId: Int) {
        ToastUtils.showShort(msgResId)
    }
}
