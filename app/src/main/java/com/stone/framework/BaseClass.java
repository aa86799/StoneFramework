package com.stone.framework;

import androidx.annotation.CallSuper;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-15 20:37
 */
public class BaseClass {

    @CallSuper
    protected void test() {
        System.out.println("base class invoke");
    }
}
