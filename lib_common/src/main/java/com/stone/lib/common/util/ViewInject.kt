package com.stone.lib.common.util

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-12 15:02
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewInject(val value: Int)

/*
 * java:
 *
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  ViewInject {
    int value();
}

 */