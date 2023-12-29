package com.app.androidtesting.roomDB.userlistVM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
class UserListData(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "tvName") val tvName: String,
        @ColumnInfo(name = "tvEmailID") val tvEmailID: String,
        @ColumnInfo(name = "tvImageURl") val tvImageURl: String,
        @ColumnInfo(name = "profile") val profile: String,
        @ColumnInfo(name = "yexp") val yexp: String
)
/*

@Entity(tableName = "user_data")
data class UserListData(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long = 0,

        @ColumnInfo(name = "tvName")
        val tvName: String,

        @ColumnInfo(name = "tvEmailID")
        val tvEmailID: String,

        @ColumnInfo(name = "tvImageURl")
        val tvImageURl: String,

        @ColumnInfo(name = "profile")
        val profile: String,

        @ColumnInfo(name = "yexp")
        val yexp: String
    )*/
