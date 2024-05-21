package com.app.cleanarchitecture.application

import android.app.Application
import android.content.Context

class BaseApplication:Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}