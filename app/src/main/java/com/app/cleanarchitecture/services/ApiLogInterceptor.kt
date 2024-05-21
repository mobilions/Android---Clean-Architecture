package com.app.cleanarchitecture.services

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException


class ApiLogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val builder = request.newBuilder()

       // builder.addHeader("", "")

        val newRequest = builder.build()

        val t1 = System.nanoTime()
        val response = chain.proceed(newRequest)
        val t2 = System.nanoTime()

        val res = response

        Log.e("headers:", "------------ \n" + newRequest.headers)

        Log.e("URL", response.code.toString() + " " + request.method + " " + request.url.toString())
        Log.e("REQ:", "--- " + bodyToString(newRequest))
        return response
    }


    companion object {
        fun bodyToString(request: Request): String {
            return try {
                if (request.body != null) {
                    val copy = request.newBuilder().build()
                    val buffer = Buffer()
                    copy.body!!.writeTo(buffer)
                    return buffer.readUtf8()
                }
                ""
            } catch (e: IOException) {
                "did not work"
            }
        }
    }
}