package com.stone.framework.bean

import javax.inject.Inject

class User {
     var name: String? = null

    @Inject
    constructor() {
    }

//    @Inject
    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return super.toString()+"User(name='$name')"
    }



}
