package com.picpay.desafio.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.database.dao.UserDAO
import com.picpay.desafio.android.model.User

private const val DATABASE_NAME ="users.db"
@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract val userDAO :UserDAO

    companion object {
        private lateinit var db: AppDataBase

        fun getInstance(context: Context): AppDataBase{

            if (::db.isInitialized) {
                return db
            }

            db = Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DATABASE_NAME
            ).build()
            return db
        }

    }

}