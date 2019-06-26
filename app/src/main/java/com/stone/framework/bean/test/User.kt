package com.stone.framework.bean.test

import javax.inject.Inject

class User {
    var name: String? = null

    @Inject
    constructor() {
    }

//    @Inject  //只能有一个用于构造函数
    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return super.toString() + "User(name='$name')"
    }

}
