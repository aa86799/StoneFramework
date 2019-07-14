package com.stone.lib.common.router.template;

import com.stone.lib.annotations.router.entity.RouteMeta;

import java.util.Map;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 06:22
 */
public interface IRouteGroup {

    /**
     * @param atlas 保存 一个组内，所有的 RouteMeta
     */
    void loadInto(Map<String, RouteMeta> atlas);
}
