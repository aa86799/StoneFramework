package com.stone.lib.common.router

import com.stone.lib.annotations.router.entity.RouteMeta
import com.stone.lib.common.router.template.IRouteGroup
import com.stone.lib.common.router.template.IService

import java.util.HashMap

object Warehouse {

    // root 映射表 保存分组信息
    internal var groupsIndex: MutableMap<String, Class<out IRouteGroup>> = HashMap()

    // group 映射表 保存组中的所有数据
    internal var routes: MutableMap<String, RouteMeta> = HashMap()

    // group 映射表 保存组中的所有数据
    internal var services: MutableMap<Class<*>, IService> = HashMap()
    // TestServiceImpl.class , TestServiceImpl 没有再反射


}
