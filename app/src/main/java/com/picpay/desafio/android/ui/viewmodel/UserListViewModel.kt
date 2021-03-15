package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.Resource
import com.picpay.desafio.android.repository.UserRepository

class UserListViewModel(private val repository: UserRepository): ViewModel() {

    fun getAllUsersModel() : LiveData<Resource<List<User>?>>{

        return repository.getAllUsers()
    }

}