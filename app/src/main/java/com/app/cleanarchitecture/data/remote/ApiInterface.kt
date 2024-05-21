package com.app.cleanarchitecture.data.remote

import com.app.cleanarchitecture.data.model.UserDataResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("api/")
    suspend fun getUserDataAPI():Response<UserDataResponse>

}