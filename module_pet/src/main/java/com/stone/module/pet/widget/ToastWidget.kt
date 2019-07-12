package com.stone.module.pet.widget

import android.view.View
import android.widget.Toast
import com.stone.module.pet.App

class ToastWidget private constructor(builder: Builder) {

    class Builder {
        var message: String? = null
        var messageResId: Int = 0
        var view: View? = null
        var viewResId: Int = 0
        var gravity: Int = -1

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun messageResId(messageResId: Int): Builder {
            this.messageResId = messageResId
            return this
        }

        fun view(view: View): Builder {
            this.view = view
            return this
        }

        fun view(viewResId: Int): Builder {
            this.viewResId = viewResId
            return this
        }

        fun gravity(gravity: Int): Builder {
            this.gravity = gravity
            return this
        }

        fun build(): ToastWidget {
            return ToastWidget(this)
        }
    }

    companion object {
        //此处的 mToast 有静态单例效果
        private var mToast: Toast? = Toast.makeText(App.getInstance(), "", Toast.LENGTH_SHORT)
    }

    init {
        if (builder.message != null)
            mToast?.setText(builder.message)

        if (builder.messageResId != 0)
            mToast?.setText(builder.messageResId)

        if (builder.view != null) {
            mToast?.view = builder.view
        }

        if (builder.viewResId != 0) {
            mToast?.view = View.inflate(App.getInstance(), builder.viewResId, null)
        }

        if (builder.gravity != -1) {
            mToast?.setGravity(builder.gravity, 0, 0)
        }

    }

    fun showShort() {
        mToast?.duration = Toast.LENGTH_SHORT
        mToast?.show()
    }

    fun showLong() {
        mToast?.duration = Toast.LENGTH_LONG
        mToast?.show()
    }

    fun cancel() {
        mToast?.cancel()
    }

    fun getView():View? {
        return mToast?.view
    }
}
