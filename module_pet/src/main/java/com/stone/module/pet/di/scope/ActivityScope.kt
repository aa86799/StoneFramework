package com.stone.module.pet.di.scope

import javax.inject.Scope
import kotlin.annotation.MustBeDocumented
import kotlin.annotation.Retention

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope


/*
 * 实现与 @Singleton 一样的；
 * @Singleton 是局部单例，即注入(inject) 到哪个 对象中，即在哪个对象生命周期内保持单例；
 * 当 Component，dependencies 某组件时，不能与 依赖的组件有同名的 局部单例 注解；
 *      基于此，需要自定义一个 scope
 */