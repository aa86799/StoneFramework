package com.stone.framework.config

import com.stone.framework.App

object GlobalConfig {

    fun getApp(): App? {
        return App.getInstance()
    }
}