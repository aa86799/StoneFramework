package com.stone.module.pet.api

import com.stone.module.pet.bean.api.BeanResponse
import com.stone.module.pet.bean.api.PageBean
import com.stone.module.pet.bean.test.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * 首页
     * @return
     */
    @GET("index.php")
    fun getHomeIndex(): Observable<BeanResponse<PageBean<HomeBean>>>

//    /**
//     * 获取我的动态列表
//     * @param token
//     * @param current 分页-页数，第几页
//     * @return
//     */
//    @GET("/apiDriver/getPendingOrderList")
//    fun getDriverDetail(@Query("token") type: String, @Query("current") current: Int):
//            Observable<BeanResponse<List<PageBean<PendingOrderEntity>>>>


}