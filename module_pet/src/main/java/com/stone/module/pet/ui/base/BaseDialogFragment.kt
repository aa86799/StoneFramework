package com.stone.module.pet.ui.base

import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView
import com.stone.lib.common.ui.base.BaseCompatDialogFragment
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.FragmentEvent
import me.yokeyword.fragmentation.ISupportFragment
import java.lang.reflect.ParameterizedType

/**
 * desc   : 结合了 SupportFragment RxFragment
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/6/20 18:26
 */
abstract class BaseDialogFragment<P : BasePresenter<*>> : BaseCompatDialogFragment(), ISupportFragment,
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
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            mPresenter?.attachView(this)
        }

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