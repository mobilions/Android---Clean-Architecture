package com.app.cleanarchitecture.presentation.user.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cleanarchitecture.common.Resource
import com.app.cleanarchitecture.doamin.usecase.UserUseCase
import com.app.cleanarchitecture.presentation.user.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class UserViewModel : ViewModel() {

    private val userUseCase: UserUseCase = UserUseCase()

    private val _data = MutableStateFlow<UserState>(UserState())
    val userState: StateFlow<UserState> = _data

    fun getUserData() {
        userUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _data.value = UserState(isLoading = true)
                }

                is Resource.Error -> {
                    _data.value = UserState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    _data.value = UserState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}