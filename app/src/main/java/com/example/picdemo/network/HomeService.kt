package com.example.picdemo.network

import com.example.picdemo.bean.PicListItem
import com.example.picdemo.bean.RestResult
import retrofit2.Call
import retrofit2.http.*

/**
 * 类描述:
 * 创建日期:2020/11/17 on 0:47
 * 作者:逢朋
 */
interface HomeService {

    @Headers("Content-Type: application/json")
    @GET("j")
    suspend fun getPic(@Query("q") keyWord: String, @Query("sn") page: Int, @Query("pn") pageSize: Int): RestResult<MutableList<PicListItem>>


    @Headers("Content-Type: application/json")
    @GET("j")
    fun getPic2(@Query("q") keyWord: String, @Query("sn") page: Int, @Query("pn") pageSize: Int): Call<RestResult<MutableList<PicListItem>>>

}