package com.stone.lib.common.router.template


import com.stone.lib.annotations.router.entity.RouteMeta

interface IRouteGroup {

    /**
     * @param atlas 保存 一个组内，所有的 RouteMeta
     */
    fun loadInto(atlas: Map<String, RouteMeta>)
}
