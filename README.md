# 简介
    整体项目采用组件化配置。
    大部份代码采用 kotlin 书写。
    MVP + Dagger2 + Okhttp3 + Retrofit2 + RxJava2 + Rxlifecycle + fragmentation + BRVAH + ...

# 配置
    详见 *** configs2.gradle *** 
    
# 组件化遇到的问题
     - 用了 butterknife，library 必须用 R2，application 必须使用 R 来定位资源。
    在切换 集成/组件 模式时，R 要改成 R2，或 R2 要改成 R，非常麻烦。
    所以还是不使用 Butterknife 比较好。
    
     - 切换了模式后，可能造成编译报红，但运行正常的情况。重新打开项目可以解决。
     
# RouteProcessor
    处理 @Route 。
    生成 名如 $$Root$$ 类，保存各组信息；为每个组，生成名如 $$Group$$ 类，保存每组的路由数据(RouteMeta)。