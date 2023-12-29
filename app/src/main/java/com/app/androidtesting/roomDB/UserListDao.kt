package com.app.androidtesting.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.androidtesting.roomDB.userlistVM.UserListData

@Dao
interface UserListDao {

    @Query("SELECT * FROM user_data ORDER BY id ASC")
    suspend fun getUserList(): List<UserListData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(word: UserListData)

    @Update
    suspend fun update(userListData: UserListData)

    @Query("DELETE FROM user_data")
    suspend fun deleteAllUsers()
}

/*
@Dao
interface UserListDao {

    @Query("SELECT * FROM user_data ORDER BY tvName ASC")
    fun getAlphabetizedWords(): List<UserListData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: UserListData)

    @Query("DELETE FROM user_data")
    suspend fun deleteAll()

}
*/
