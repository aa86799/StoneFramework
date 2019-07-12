package com.stone.module.pet.ui.base

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.stone.module.pet.R
import com.stone.lib.common.ui.base.BaseCompatActivity
import com.stone.lib.common.mvp.BasePresenter
import com.stone.lib.common.mvp.BaseView
import com.stone.module.pet.widget.ToastWidget
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
        }
    }

    override fun initToolBar() {
        val tb: Toolbar = findViewById(R.id.layout_header_toolbar) ?: return
        setSupportActionBar(tb)
        supportActionBar?.setDisplayShowTitleEnabled(false) //去除默认title显示

        tb.findViewById<View>(R.id.layout_header_top_left_iv)
            .setOnClickListener { finish() }
//        tb.setNavigationIcon()
//        tb.setNavigationOnClickListener {  }
    }

    protected fun onFinish() {
        finish()
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

   /* protected fun navBuild(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

    protected fun navigation(path: String): Any {
        return ARouter.getInstance().build(path).navigation()
    }*/

    protected fun cancelToastWidget() {
        toastWidget?.cancel() //通常只在退出应用前的一个Activity中调用，如 MainActivity中
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }
}
