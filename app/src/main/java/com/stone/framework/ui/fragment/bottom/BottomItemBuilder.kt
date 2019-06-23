package com.stone.framework.ui.fragment.bottom


/**
 * desc     : 存储底部菜单项集合
 * author   : stone
 *
 * email    : aa86799@163.com
 * time     : 2018/7/10 15 29
 */
class BottomItemBuilder {

    internal val ITEMS = mutableMapOf<BottomTabBean, BottomItemFragments>()


    fun addItem(tabBean: BottomTabBean, itemDelegate: BottomItemFragments): BottomItemBuilder {
        ITEMS[tabBean] = itemDelegate
        return this
    }

    fun addItems(items: MutableMap<BottomTabBean, BottomItemFragments>): BottomItemBuilder {
        ITEMS.clear()
        ITEMS.putAll(items)
        return this
    }

}
