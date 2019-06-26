package com.stone.framework.config.api

import com.stone.framework.bean.PendingOrderEntity
import com.stone.framework.bean.test.HomeEntity
import com.stone.framework.bean.api.BeanResponse
import com.stone.framework.bean.api.PageBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * 首页
     * @return
     */
    @GET("/getindexdata")
    fun getHomeIndex(): Observable<BeanResponse<List<HomeEntity>>>

    /**
     * 获取我的待接单列表
     * @param token
     * @param current 分页-页数，第几页
     * @return
     */
    @GET("/apiDriver/getPendingOrderList")
    fun getDriverDetail(@Query("token") type: String, @Query("current") current: Int):
            Observable<BeanResponse<List<PageBean<PendingOrderEntity>>>>


}