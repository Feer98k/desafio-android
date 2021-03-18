package com.picpay.desafio.android.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.repository.Resource
import com.picpay.desafio.android.domain.GetUserCase

class UserListViewModel(private  val userCase : GetUserCase): ViewModel() {

  fun getAllUsersModel():  LiveData<Resource<List<User>?>>{
      return userCase.getListUsers()
  }

}