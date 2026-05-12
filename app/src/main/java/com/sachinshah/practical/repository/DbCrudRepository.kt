package com.sachinshah.practical.repository

import androidx.lifecycle.LiveData
import com.sachinshah.practical.ApiResponse
import com.sachinshah.practical.model.LoginRequest
import com.sachinshah.practical.model.QuoteData
import com.sachinshah.practical.retrofit.ApiCall
import com.google.gson.JsonObject
import com.sachinshah.practical.MyApplication
import com.sachinshah.practical.room.dao.UserDao
import com.sachinshah.practical.room.database.AppDatabase
import com.sachinshah.practical.room.entity.UserModel
import javax.inject.Inject

class DbCrudRepository @Inject constructor(roomDatabase: AppDatabase) : BaseRepository() {

    var userDao: UserDao = roomDatabase.userDao()

    suspend fun getAllUser(
    ): ApiResponse<List<UserModel>?> {
        try {
            val response = userDao.getAllUser()
            return ApiResponse.Success(response)

        } catch (e: Exception) {
            return ApiResponse.Error(e)
        }


    }

    fun getAllUserLive(): LiveData<List<UserModel>> {
        return userDao.getAllUserLive()
    }

    suspend fun addUser(userModel: UserModel): Long {
        return performInsertWithLongId(userModel, userDao)
    }

    suspend fun updateUser(userModel: UserModel) {
        performUpdate(userModel, userDao)
    }

    suspend fun deleteUser(userModel: UserModel) {
        performDelete(userModel, userDao)
    }


}