package com.stone.framework.di.component

import com.stone.framework.di.module.ActivityModule
import com.stone.framework.ui.activity.main.ActivityMain
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: ActivityMain)
}