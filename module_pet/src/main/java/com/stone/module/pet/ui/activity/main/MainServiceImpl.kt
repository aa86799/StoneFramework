package com.stone.module.pet.ui.activity.main

import com.stone.lib.annotations.router.Route

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-13 23:31
 */
@Route(path = "/Pet/mainServiceImpl")
class MainServiceImpl : IMainService {

    override fun load(data: String) {
        println("stone->data=$data")
    }
}
