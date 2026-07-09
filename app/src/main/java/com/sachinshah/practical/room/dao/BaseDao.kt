package com.sachinshah.practical.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.sachinshah.practical.room.entity.UserModel

interface BaseDao<T> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(t: ArrayList<T>): LongArray

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(t: T): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(t: ArrayList<T>): Int

    @Delete
    suspend fun delete(t: T)

    @Delete
    suspend fun deleteAll(t: ArrayList<T>)


}

