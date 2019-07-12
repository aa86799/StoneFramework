package com.stone.module.pet.bean.api

import java.io.Serializable

/**
 * desc   : 分页
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019-07-12 11:27
 */

class PageBean<T> : Serializable {

    var pages: Int = 0 //共多少页
    var current: Int = 0 //当前页数
    var size: Int = 0 //当前页的size
    var total: Int = 0 //总共的 size
    var records: List<T>? = null //记录
    var extra: String? = null

}
