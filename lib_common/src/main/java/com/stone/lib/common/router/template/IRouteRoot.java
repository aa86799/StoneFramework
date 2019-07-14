package com.stone.lib.common.router.template;

import java.util.Map;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-07-14 06:20
 */
public interface IRouteRoot {

    /**
     * 以 key，即分组，来保存 IRouteGroup
     * @param routes
     */
    void loadInto(Map<String, Class<? extends IRouteGroup>> routes);
}
