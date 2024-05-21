package com.app.cleanarchitecture.presentation.user

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.app.cleanarchitecture.common.ImageLoader
import com.app.cleanarchitecture.databinding.ActivityUserBinding
import com.app.cleanarchitecture.presentation.user.viewModel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.swipeLayout.isRefreshing = true

        binding.swipeLayout.setOnRefreshListener(OnRefreshListener {

            getUserData()

        })
    }

    override fun onResume() {

        getUserData()

        super.onResume()
    }

    private fun getUserData() {
        GlobalScope.launch(Dispatchers.IO) {

            viewModel.getUserData()

            try {
                viewModel.userState.collect {
                    if (it.isLoading) {
                        binding.cardData.isVisible = false
                    }
                    if (it.error.isNotBlank()) {
                        Log.e("status", "error")
                    }
                    it.data?.let {

                        val data = it.results.firstOrNull()

                        CoroutineScope(Dispatchers.Main).launch {
                            binding.swipeLayout.isRefreshing = false

                            binding.cardData.isVisible = true

                            binding.tvUserName.text = data?.name?.userName()
                            binding.tvPhone.text = data?.phone
                            binding.tvCell.text = data?.cell
                            binding.tvAddress.text = data?.location?.fullAddress()

                            ImageLoader.loadImage(data?.picture?.large.toString(), binding.imgUser)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}