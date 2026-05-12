package com.sachinshah.practical.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachinshah.practical.room.dao.UserDao
import com.sachinshah.practical.room.entity.UserModel
import javax.inject.Inject


@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class AppDatabase :  RoomDatabase(){

    abstract fun userDao (): UserDao

}