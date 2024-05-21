package com.app.cleanarchitecture.services

import com.google.gson.Gson
import retrofit2.Response

abstract class SafeApiRequest {
    inline fun <reified T : Any> apiRequest(call: () -> Response<T>): T {
        val response = call.invoke()
        try {
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                val error = response.errorBody()?.string()
                if (response.code() == 401) {
                    //Handling 401 Exception

                    return Gson().fromJson(error, T::class.java) as T

                } else if  (response.code() == 500) {

                }else {
                    return Gson().fromJson(error, T::class.java) as T
                }
                throw Exception(error.toString())
            }
        } catch (e: Exception) {
            e.stackTrace
            return response.body() as T
        }
    }
}