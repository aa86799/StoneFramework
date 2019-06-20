package com.stone.framework.di.component

import com.stone.framework.di.module.FragmentModule
import dagger.Component


@Component(modules = [FragmentModule::class])
interface FragmentComponent {
}