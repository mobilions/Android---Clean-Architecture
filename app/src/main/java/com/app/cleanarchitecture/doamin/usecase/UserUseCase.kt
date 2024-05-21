package com.app.cleanarchitecture.doamin.usecase


import com.app.cleanarchitecture.common.Resource
import com.app.cleanarchitecture.data.model.UserDataResponse
import com.app.cleanarchitecture.data.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserUseCase {

    private val repository: UserListRepository = UserListRepository()

    operator fun invoke(): Flow<Resource<UserDataResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getUserData()

            emit(Resource.Success(data = response))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "UnExpected Error"))
        }
    }
}