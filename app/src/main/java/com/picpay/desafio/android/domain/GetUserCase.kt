package com.picpay.desafio.android.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.repository.Resource
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User

class GetUserCase(private val repository: UserRepository) : ViewModel() {

    fun getListUsers(): LiveData<Resource<List<User>?>> {

        return repository.getAllUsers()

    }
}