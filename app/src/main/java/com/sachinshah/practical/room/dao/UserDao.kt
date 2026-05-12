package com.sachinshah.practical.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sachinshah.practical.room.entity.UserModel

@Dao
interface UserDao : BaseDao<UserModel> {

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<UserModel>

    @Query("SELECT * FROM user")
    fun getAllUserLive(): LiveData<List<UserModel>>

    @Query("SELECT * FROM user where user_id =:id")
    suspend fun getSingleUser(id: Int): UserModel
}