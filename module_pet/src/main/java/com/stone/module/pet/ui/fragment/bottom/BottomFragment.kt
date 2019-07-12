package com.stone.module.pet.ui.fragment.bottom


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import butterknife.BindView
import com.stone.module.pet.R
import com.stone.module.pet.ui.base.BaseFragment
import com.stone.module.pet.ui.fragment.home.FragmentHome
import com.stone.module.pet.ui.fragment.home.mine.FragmentMine

/**
 * desc     : 底部菜单页，将菜单页集合等方法抽象出来，让子类去实现
 * author   : stone
 * email    : aa86799@163.com
 * time     : 2018/7/10 15 09
 */
class BottomFragment : BaseFragment<BottomPresenter>(), OnClickListener, BottomContract.View {

    private val fragments = mutableListOf<BottomItemFragment<*>>()
    private var items: MutableMap<BottomTabBean, BottomItemFragment<*>>? = null
    private var mCurrentDelegate: Int = 0 //当前显示的 delegate
    private var mClickColor = Color.RED
    private var mTextColor: Int = 0

    @JvmField
    @BindView(R.id.fragment_bottom_bar_ll)
    var mBottomBar: LinearLayoutCompat? = null

    @JvmField
    @BindView(R.id.fragment_bottom_content_fl)
    var mFrameLayout: FrameLayout? = null

    /**
     * 设置底部 item 的集合
     */
    fun setItems(builder: BottomItemBuilder): MutableMap<BottomTabBean, BottomItemFragment<*>> {
        val items = mutableMapOf<BottomTabBean, BottomItemFragment<*>>()
        items[BottomTabBean(R.mipmap.ic_launcher, "首页")] = FragmentHome()
        items[BottomTabBean(R.mipmap.ic_launcher, "动态")] = FragmentHome()
        items[BottomTabBean(R.mipmap.ic_launcher, "我的")] = FragmentMine()
        return builder.addItems(items).ITEMS
    }


    /**
     * 设置初始显示的底部菜单项，当然也会影响对应的内容显示
     *
     * @return
     */
    fun setFirstIndexDelegate(): Int {
        return 0
    }

    /**
     * 设置点击色
     *
     * @return
     */
    @ColorInt
    fun setClickColor(): Int {
        return Color.RED
    }

    fun setNormalTextColor(): Int {
        return Color.parseColor("#000000")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_bottom
    }

    override fun init() {
        mCurrentDelegate = setFirstIndexDelegate()
        mClickColor = setClickColor()
        mTextColor = setNormalTextColor()

        items = setItems(BottomItemBuilder())

        val entrySet = items!!.entries
        val tabBeans = mutableListOf<BottomTabBean>()
        for ((key, value) in entrySet) {
            tabBeans.add(key)
            fragments.add(value)
        }

        val size = items!!.size
        for (i in 0 until size) {
            LayoutInflater.from(context).inflate(R.layout.item_bottom, mBottomBar)
            val layout = mBottomBar!!.getChildAt(i) as LinearLayoutCompat
            layout.tag = i //保存索引为 tag
            layout.setOnClickListener(this)

            val bottomTabBean = tabBeans[i]
            val ivIcon = layout.getChildAt(0) as ImageView
            ivIcon.setImageResource(bottomTabBean.icon)
            val tvTitle = layout.getChildAt(1) as TextView
            tvTitle.text = bottomTabBean.title

            if (i == mCurrentDelegate) {
                tvTitle.setTextColor(mClickColor)
            } else {
                tvTitle.setTextColor(mTextColor)
            }
        }

        //可变参数展开操作符. 在数组对象前加*号可以将数组展开
        loadMultipleRootFragment(R.id.fragment_bottom_content_fl, mCurrentDelegate, *fragments.toTypedArray())

    }

    private fun resetColor() {
        val size = mBottomBar!!.childCount
        for (i in 0 until size) {
            val layout = mBottomBar!!.getChildAt(i) as LinearLayoutCompat
            val tvTitle = layout.getChildAt(1) as AppCompatTextView
            tvTitle.setTextColor(mTextColor)
        }
    }

    override fun onClick(v: View) {
        resetColor()
        val layout = v as LinearLayoutCompat
        val tvTitle = layout.getChildAt(1) as AppCompatTextView
        tvTitle.setTextColor(mClickColor)

        val tag = v.tag as Int
        showHideFragment(fragments[tag], fragments[mCurrentDelegate])
        mCurrentDelegate = tag
    }
}
