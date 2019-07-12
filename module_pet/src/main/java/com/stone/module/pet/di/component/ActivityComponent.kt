package com.stone.module.pet.di.component

import com.stone.module.pet.di.module.ActivityModule
import com.stone.module.pet.ui.activity.main.ActivityMain
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: ActivityMain)
}