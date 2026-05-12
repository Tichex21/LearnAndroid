package com.sachinshah.practical.repository

import android.content.Intent
import com.sachinshah.practical.room.dao.BaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    suspend fun <T> performInsertWithLongId(t: T, baseDao: BaseDao<T>): Long = baseDao.insert(t)


    protected suspend fun <T> performInsert(t: T, baseDao: BaseDao<T>) = baseDao.insert(t)


    protected suspend fun <T> performInsertAll(t: ArrayList<T>, baseDao: BaseDao<T>) =
        baseDao.insertAll(t)


    protected suspend fun <T> performUpdate(t: T, baseDao: BaseDao<T>) = baseDao.update(t)


    protected suspend fun <T> performUpdateAll(t: ArrayList<T>, baseDao: BaseDao<T>) =
        baseDao.updateAll(t)


    protected suspend fun <T> performDelete(t: T, baseDao: BaseDao<T>) = baseDao.delete(t)


    protected suspend fun <T> performDeleteAll(t: ArrayList<T>, baseDao: BaseDao<T>) =
        baseDao.deleteAll(t)

}