package com.stone.framework.frame

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.stone.framework.frame.compat.BaseCompatActivity
import com.stone.framework.frame.mvp.BasePresenter
import com.stone.framework.frame.mvp.BaseView
import java.lang.reflect.ParameterizedType

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/6/20 18:28
 */
//注意:此处使用星投影为了避免具体的Presenter无边界报错
abstract class BaseActivity<P : BasePresenter<*>> : BaseCompatActivity(),
    BaseView {

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
            mPresenter?.setContext(this)
        }
    }

    override fun showMsg(msg: String?) {
        ToastUtils.showShort(msg)
    }

    override fun showMsg(msgResId: Int) {
        ToastUtils.showShort(msgResId)
    }

    protected fun navBuild(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

    protected fun navigation(path: String): Any {
        return ARouter.getInstance().build(path).navigation()
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }
}
