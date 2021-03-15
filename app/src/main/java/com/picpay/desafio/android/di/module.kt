@file:Suppress("RemoveExplicitTypeArguments")

package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.database.AppDataBase
import com.picpay.desafio.android.database.dao.UserDAO
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.retrofit.webclient.UserWebClient
import com.picpay.desafio.android.ui.viewmodel.UserListViewModel
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
    viewModel<UserListViewModel> {
        UserListViewModel(get())
    }
}