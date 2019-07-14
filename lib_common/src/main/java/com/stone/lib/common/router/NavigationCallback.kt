package com.stone.lib.common.router


/**
 */
interface NavigationCallback {

    /**
     * 找到跳转页面
     *
     * @param postcard meta
     */
    fun onFound(postcard: Postcard)

    /**
     * 未找到
     *
     * @param postcard meta
     */
    fun onLost(postcard: Postcard)

    /**
     * 成功跳转
     *
     * @param postcard meta
     */
    fun onArrival(postcard: Postcard)

}
