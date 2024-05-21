package com.app.cleanarchitecture.presentation.user.state

import com.app.cleanarchitecture.data.model.UserDataResponse


data class UserState(
    val data:UserDataResponse? = null,
    val error:String = "",
    val isLoading:Boolean = false
)