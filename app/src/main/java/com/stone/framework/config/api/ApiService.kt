package com.stone.framework.config.api

import com.stone.framework.bean.HomeEntity
import com.stone.framework.bean.api.BeanResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    /**
     * 首页
     * @return
     */
    @GET("/getindexdata")
    abstract fun getHomeIndex(): Observable<BeanResponse<List<HomeEntity>>>
}