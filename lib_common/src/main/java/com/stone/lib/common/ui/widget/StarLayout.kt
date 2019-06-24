package com.stone.lib.common.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.stone.lib.common.R


/**
 * desc   : 星星仅展示
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/6/24 15:19
 */
class StarLayout constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayoutCompat(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)


    init {
        initStartIcon()
    }

    private fun initStartIcon() {
        var star: ImageView
        var lp: LayoutParams
        for (i in 0 until STAR_TOTAL_COUNT) {
            star = ImageView(context)
            //            lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
            lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
            lp.weight = 1f
            lp.gravity = Gravity.CENTER
            star.layoutParams = lp

            star.background = context.resources.getDrawable(ICON_BORDER)

            addView(star)
        }
    }

    fun setScore(score: Float) {
        var fullCount = score.toInt()
        for (i in 0 until fullCount) {
            val star = getChildAt(i) as ImageView
            star.background = context.resources.getDrawable(ICON_FULL)
        }
        //紧跟着判断半个的情况
        if (fullCount < STAR_TOTAL_COUNT) {
            if (score - fullCount * 1.0f >= 0.5f) {
                (getChildAt(fullCount) as ImageView).background = context.resources.getDrawable(ICON_HALF)
                fullCount++
            }
        }

        //后面的空心
        for (i in fullCount until STAR_TOTAL_COUNT) {
            val star = getChildAt(i) as ImageView
            star.background = context.resources.getDrawable(ICON_BORDER)
        }
    }

    companion object {
        private val ICON_FULL = R.drawable.ic_star_yellow_24dp
        private val ICON_HALF = R.drawable.ic_star_half_yellow_24dp
        private val ICON_BORDER = R.drawable.ic_star_border_yellow_24dp
        private val ICON_BORDER_GRAY = R.drawable.ic_star_border_gray_24dp
        private val STAR_TOTAL_COUNT = 5
    }
}
