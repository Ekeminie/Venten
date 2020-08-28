package com.example.venten.service

import com.example.venten.model.Filter
import retrofit2.Call
import retrofit2.http.GET

interface FilterService {

    @GET("filter.json")
    fun getFilters () : Call<Filter>

    @GET("/")
    fun gett() : Call<Filter>
}