package com.stone.lib.common.router.template

interface IRouteRoot {

    /**
     * 以 key，即分组，来保存 IRouteGroup
     * @param routes
     */
    fun loadInto(routes: Map<String, Class<out IRouteGroup>>)
}
