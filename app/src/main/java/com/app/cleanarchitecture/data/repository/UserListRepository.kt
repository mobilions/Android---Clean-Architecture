package com.app.cleanarchitecture.data.repository

import com.app.cleanarchitecture.services.ApiClient.apiInterface
import com.app.cleanarchitecture.services.SafeApiRequest


class UserListRepository : SafeApiRequest() {

    suspend fun getUserData() = apiRequest {
        apiInterface.getUserDataAPI()
    }

}