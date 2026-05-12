package com.sachinshah.practical.module

import android.content.Context
import androidx.room.Room
import com.sachinshah.practical.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQSkillDatabase(@ApplicationContext context: Context): AppDatabase {
        return synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "app_database"
            ).fallbackToDestructiveMigrationOnDowngrade(true).build()
            instance
        }
    }
}