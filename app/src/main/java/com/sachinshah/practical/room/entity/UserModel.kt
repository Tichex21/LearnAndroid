package com.sachinshah.practical.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("user_id") var uid: Int=0,
    @ColumnInfo(name = "first_name") val firstName: String?="",
    @ColumnInfo(name = "last_name") val lastName: String?=""

) : Parcelable


@Entity(tableName = "Messages")
@Parcelize
data class MsgModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("user_id")
    var id: Int=0




): Parcelable