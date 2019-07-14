package com.stone.module.pet.config

import android.app.Application


object PetGlobalConfig {

    var app: Application? = null

    fun init(application: Application) {
        this.app = application


    }
}