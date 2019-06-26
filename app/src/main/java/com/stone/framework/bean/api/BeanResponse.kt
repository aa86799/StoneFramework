package com.stone.framework.bean.api


class BeanResponse<T> {
    var message: String? = null
    var rescode: Int = 0
    var success: Boolean = false
    var data: T? = null
}
