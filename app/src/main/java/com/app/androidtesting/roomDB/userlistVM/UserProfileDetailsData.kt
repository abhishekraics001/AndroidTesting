package com.app.androidtesting.roomDB.userlistVM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_profile_details")
class UserProfileDetailsData(
        @PrimaryKey(autoGenerate = true) val id: Int = 1,
        @ColumnInfo(name = "tvName") val tvName: String,
        @ColumnInfo(name = "tvEmailID") val tvEmailID: String,
        @ColumnInfo(name = "tvImageURl") val tvImageURl: String,
        @ColumnInfo(name = "profile") val profile: String,
        @ColumnInfo(name = "yexp") val yexp: String
)