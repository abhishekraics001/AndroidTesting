package com.app.androidtesting.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.app.androidtesting.roomDB.userlistVM.UserProfileDetailsData

@Dao
interface UserListDao {

    //@Insert(onConflict = OnConflictStrategy.ABORT)
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(usr: UserProfileDetailsData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userList: List<UserProfileDetailsData>)

 /*   @Transaction
    suspend fun insertUserList(userList: List<UserProfileDetailsData>) {
        userList.forEach { user ->
            deleteAllUsers()
            insertUser(user)
        }
    }*/



    @Query("SELECT * FROM users_profile_details ORDER BY id ASC")
    suspend fun getAllUsersList(): List<UserProfileDetailsData>

    @Query("SELECT * FROM users_profile_details WHERE id = :id ORDER BY tvName LIMIT 1")
    suspend fun getUserByID(id: String): List<UserProfileDetailsData>


    @Query("SELECT * FROM users_profile_details WHERE tvName LIKE :first AND yexp = :year LIMIT 1")
    fun loadOneByNameAndReleaseYear(first: String?, year: Int): UserProfileDetailsData?



    @Update
    suspend fun update(userListData: UserProfileDetailsData): Int

    @Query("UPDATE users_profile_details SET tvName = :userName WHERE id = :userId")
    suspend fun updateUserName(userName: String, userId: String) : Int

    @Update
    fun updateListOfUsers(songs: List<UserProfileDetailsData>): Int



    @Query("DELETE FROM users_profile_details")
    suspend fun deleteAllUsers() : Int

    @Query("DELETE FROM users_profile_details WHERE id = :id")
    suspend fun deleteUserByID(id: String)
}
