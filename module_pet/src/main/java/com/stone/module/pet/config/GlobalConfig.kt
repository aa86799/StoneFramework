package com.stone.module.pet.config

import com.stone.module.pet.App

object GlobalConfig {

    fun getApp(): App? {
        return App.getInstance()
    }
}