package com.stone.framework.frame

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.stone.framework.R
import com.stone.framework.frame.compat.BaseCompatActivity
import com.stone.framework.frame.mvp.BasePresenter
import com.stone.framework.frame.mvp.BaseView
import com.stone.framework.widget.ToastWidget
import java.lang.reflect.ParameterizedType

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/6/20 18:28
 */
//注意:此处使用星投影为了避免具体的Presenter无边界报错
abstract class BaseActivity<P : BasePresenter<*>> : BaseCompatActivity(), BaseView {

    protected var mPresenter: P? = null

    private var toastWidget: ToastWidget? = null


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

    private fun initToastWidget() {
        if (null == toastWidget) {
            val view = View.inflate(this, R.layout.layout_toast, null)
            toastWidget = ToastWidget.Builder()
                .view(view)
                .gravity(Gravity.CENTER)
                .build()
        }
    }

    override fun showMsg(msg: String) {
//        ToastUtils.showShort(msg)

//        ToastWidget.Builder()
//            .message(msg)
//            .build()
//            .showShort()

        initToastWidget()

        val tv = toastWidget?.getView()?.findViewById<TextView>(R.id.layout_toast_tv)
        tv?.text = resources.getString(R.string.toast_placeholder, msg)
        toastWidget?.showShort()
    }

    override fun showMsg(msgResId: Int) {
//        ToastUtils.showShort(msgResId)

        initToastWidget()

        val tv = toastWidget?.getView()?.findViewById<TextView>(R.id.layout_toast_tv)
        val msg = resources.getString(msgResId)
        tv?.text = resources.getString(R.string.toast_placeholder, msg)
        toastWidget?.showShort()
    }

    protected fun navBuild(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

    protected fun navigation(path: String): Any {
        return ARouter.getInstance().build(path).navigation()
    }

    protected fun cancelToastWidget() {
        toastWidget?.cancel() //通常只在退出应用前的一个Activity中调用，如 MainActivity中
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }
}
