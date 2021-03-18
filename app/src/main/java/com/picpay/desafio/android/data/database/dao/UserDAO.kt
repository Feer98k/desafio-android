package com.picpay.desafio.android.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.picpay.desafio.android.domain.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAllUsersIntern(): LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun getSampleList(): List<User>

    @Insert(onConflict = REPLACE)
    fun saveUsers(user: List<User>)

    @Update(onConflict = REPLACE)
    fun updateUser(user: List<User>)


}