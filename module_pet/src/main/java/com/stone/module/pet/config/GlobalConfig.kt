package com.stone.module.pet.config

import android.app.Application
import com.stone.router.PetService
import io.github.prototypez.appjoint.AppJoint


object GlobalConfig {

    var app: Application? = null

    val PET_SERVICE:PetService = AppJoint.service(PetService::class.java)
}