package com.stone.framework.bean.api

class PageBean<T> {
    var records: List<T>? = null
    var current: Int = 0
    var pages: Int = 0
    var size: Int = 0
    var total: Int = 0
    var extra: String? = null
}
