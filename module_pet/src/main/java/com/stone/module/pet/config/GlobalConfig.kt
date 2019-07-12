package com.stone.module.pet.config

import component.App

object GlobalConfig {

    fun getApp(): App? {
        return App.getInstance()
    }
}