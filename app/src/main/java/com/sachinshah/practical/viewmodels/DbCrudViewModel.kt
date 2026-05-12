package com.sachinshah.practical.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinshah.practical.ApiResponse
import com.sachinshah.practical.model.LoginRequest
import com.sachinshah.practical.model.QuoteData
import com.sachinshah.practical.repository.QuotesDataRepository
import com.google.gson.JsonObject
import com.sachinshah.practical.repository.DbCrudRepository
import com.sachinshah.practical.room.entity.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DbCrudViewModel @Inject constructor(
    private val dbCrudRepository: DbCrudRepository
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<ApiResponse<List<UserModel>?>>()
    val usersLiveData: LiveData<ApiResponse<List<UserModel>?>> get() = _usersLiveData

    val getAllUserLiveData:LiveData<List<UserModel>>  get() = dbCrudRepository.getAllUserLive()


    fun getAllUser() {
        viewModelScope.launch {
            _usersLiveData.value = ApiResponse.ShowLoading()
            try {
                val data = dbCrudRepository.getAllUser()
                _usersLiveData.value = data
            } catch (e: Exception) {
                _usersLiveData.value = ApiResponse.Error(e)
            } finally {
                _usersLiveData.postValue(ApiResponse.HideLoading())
            }
        }

    }



    fun addUser(userModel: UserModel) {
        viewModelScope.launch {
            val id = dbCrudRepository.addUser(userModel)
            Log.d("addUser : id ", "$id")
        }

    }

    fun updateUser(userModel: UserModel) {
        viewModelScope.launch {
            dbCrudRepository.updateUser(userModel)
        }

    }

    fun deleteUser(userModel: UserModel) {
        viewModelScope.launch {
            dbCrudRepository.deleteUser(userModel)
        }
    }

}