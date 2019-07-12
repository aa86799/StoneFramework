package com.stone.module.pet.bean.api

import java.io.Serializable


class BeanResponse<T> : Serializable {
    var message: String? = null
    var code: Int = 0
    var success: Boolean = false
    var data: T? = null
}
