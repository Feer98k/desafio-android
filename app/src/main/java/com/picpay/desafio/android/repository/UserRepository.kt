package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.database.dao.UserDAO
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.retrofit.webclient.UserWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(
    private val webClient: UserWebClient,
    private val userDAO: UserDAO) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val mediator = MediatorLiveData<Resource<List<User>?>>()

    fun getAllUsers(): LiveData<Resource<List<User>?>> {
        coroutineScope.launch {
            mediator.addSource(getAllUsersOnDB()) { usersFound ->
                mediator.value = Resource(data = usersFound)
            }

            val failureWebLiveDataAPI = MutableLiveData<Resource<List<User>?>>()
            mediator.addSource(failureWebLiveDataAPI) { failureResource ->
                val actualResource = mediator.value
                val refreshResource: Resource<List<User>?> = if (actualResource != null) {
                    Resource(data = actualResource.data, error = failureResource.error)
                } else {
                    failureResource
                }
                mediator.value = refreshResource
            }

            searchUsersOnAPI(
                onFailure = { error ->
                    failureWebLiveDataAPI.value = Resource(data = null, error = error)
                })

        }

        return mediator
    }

    private fun searchUsersOnAPI(
        onFailure: (error: String?) -> Unit
    ) {
        coroutineScope.launch {
            webClient.getAllUsers(
                onSuccess = { users ->
                    users?.let {
                            saveInternalData(users)
                    }
                }, onFailure = onFailure
            )
        }
    }

    private fun saveInternalData(
        userList: List<User>
    ) {
        coroutineScope.launch {
            val temporaryList = getSampleList()
            if (temporaryList.isNullOrEmpty()) {
                userDAO.saveUsers(userList)
            }
            userDAO.updateUser(userList)
        }
    }

    private fun getAllUsersOnDB(): LiveData<List<User>> {
        return userDAO.getAllUsersIntern()
    }
    private  fun getSampleList(): List<User>{
        return  userDAO.getSampleList()
    }

}