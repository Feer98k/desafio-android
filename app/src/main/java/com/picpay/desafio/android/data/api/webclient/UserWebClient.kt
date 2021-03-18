package com.picpay.desafio.android.data.api.webclient

import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.api.AppRetrofit
import com.picpay.desafio.android.data.api.service.PicPayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private  const val message = R.string.error.toString()

class UserWebClient(private val service: PicPayService = AppRetrofit().service) {

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
        onFailure: (error: String?) -> Unit) {
        executeRequest(
            service.getUsers(),
            onSuccess,
            onFailure
        )
    }
}