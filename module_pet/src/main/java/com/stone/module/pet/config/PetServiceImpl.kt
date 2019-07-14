package com.stone.module.pet.config

import android.app.Application
import com.stone.router.PetService

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 14:21
 */
object PetServiceImpl: PetService {

    override fun setApplication(app: Application) {
        PetGlobalConfig.app = app
    }
}