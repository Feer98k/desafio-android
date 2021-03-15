package com.picpay.desafio.android.retrofit.webclient

import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.retrofit.AppRetrofit
import com.picpay.desafio.android.retrofit.service.PicPayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserWebClient(private val service: PicPayService = AppRetrofit().service) {
    private val message = R.string.error.toString()


    private fun <T> executeRequest(
        call: Call<T>,
        onSuccess: (users: T?) -> Unit,
        onFailure: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onFailure(message)
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                onFailure(t.message)
            }

        })

    }

    fun  getAllUsers(
        onSuccess: (users : List<User>?) -> Unit,
        onFailure: (error: String?) -> Unit
    ) {
        executeRequest(
            service.getUsers(),
            onSuccess,
            onFailure
        )

    }
}