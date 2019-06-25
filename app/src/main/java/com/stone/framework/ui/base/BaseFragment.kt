package com.stone.framework.ui.base

import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView
import com.stone.lib.common.ui.base.BaseCompatFragment
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.FragmentEvent
import me.yokeyword.fragmentation.ISupportFragment
import java.lang.Exception
import java.lang.reflect.ParameterizedType

/**
 * desc   : 结合了 SupportFragment RxFragment
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/6/20 18:26
 */
abstract class BaseFragment<P : BasePresenter<*>> : BaseCompatFragment(), ISupportFragment,
    LifecycleProvider<FragmentEvent>, BaseView {

    protected var mPresenter: P? = null


    @Suppress("UNCHECKED_CAST")
    override fun initPresenter() {
        if (this::class.java.genericSuperclass is ParameterizedType
            && (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments.isNotEmpty()
        ) {

            try {
                val mPresenterClass =
                    (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
                mPresenter = mPresenterClass.newInstance() as P
            } catch (e: Exception) {
                e.printStackTrace()
            }

            mPresenter?.attachView(this)
            mPresenter?.setContext(context!!)
        }
    }

    fun <T : BaseFragment<*>> getParentDelegate(): T {
        return parentFragment as T
    }

    override fun showMsg(msg: String) {
//        ToastUtils.showShort(msg)
        (activity as BaseActivity<*>).showMsg(msg)
    }

    override fun showMsg(msgResId: Int) {
//        ToastUtils.showShort(msgResId)
        (activity as BaseActivity<*>).showMsg(msgResId)
    }

//    protected fun navBuild(path: String): Postcard {
//        return ARouter.getInstance().build(path)
//    }
//
//    protected fun navigation(path: String): Any {
//        return ARouter.getInstance().build(path).navigation()
//    }

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }
}