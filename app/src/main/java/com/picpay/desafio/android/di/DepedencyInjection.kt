@file:Suppress("RemoveExplicitTypeArguments")

package com.picpay.desafio.android

import androidx.room.Room
import com.picpay.desafio.android.data.database.AppDataBase
import com.picpay.desafio.android.data.database.dao.UserDAO
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.api.webclient.UserWebClient
import com.picpay.desafio.android.domain.GetUserCase
import com.picpay.desafio.android.presenter.viewmodel.UserListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATABASE_NAME = "users.db"

val appModules = module {
    single<AppDataBase> {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    single<UserDAO> {
        get<AppDataBase>().userDAO
    }
    single<UserWebClient> {
        UserWebClient()
    }
    single<UserRepository> {
        UserRepository(get(), get())
    }
    single<GetUserCase> {
        GetUserCase(get())
    }
    viewModel<UserListViewModel> {
        UserListViewModel(get())
    }
}