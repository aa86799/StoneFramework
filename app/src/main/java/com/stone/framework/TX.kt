package com.stone.framework

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-15 20:16
 */


open class BaseClass2: BaseClass(), Inter {
    override fun test() {
        super.test()
        println("base2 class invoke")
    }
}

class SubClass: BaseClass2() {
    override fun test() {
        super.test()
        println("sub class invoke")
    }
}

fun main() {
    val inter = SubClass()
    inter.test() //没问题


    val iclz = SubClass::class.java
    val instance = iclz.newInstance()
    val sub = instance as Inter
    sub.test() //也没问题


}