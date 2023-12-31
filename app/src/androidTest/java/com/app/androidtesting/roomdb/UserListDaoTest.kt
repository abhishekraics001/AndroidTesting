package com.app.androidtesting.roomdb

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.androidtesting.roomDB.AppDatabase
import com.app.androidtesting.roomDB.UserListDao
import com.app.androidtesting.roomDB.userlistVM.UserProfileDetailsData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class UserListDaoTest {

    private lateinit var userDao: UserListDao
    private lateinit var db: AppDatabase


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder( context, AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() = runBlocking {
        //assert(true)

        // Given a user
        val user = UserProfileDetailsData(
            id = 1,
            tvName = "George Bluth",
            tvEmailID = "george.bluth@reqres.in",
            tvImageURl = "https://reqres.in/img/faces/1-image.jpg",
            profile = "Android Developer",
            yexp = "10"
        )

        // When inserting the user into the database
       val userID = userDao.insertUser(user)
        println("Inserted userID: $userID")

        // Then retrieving the user should return the same user
        val retrievedUser = userDao.getUserByID("1")

        val usersx = retrievedUser[0]
        println("retrievedUser:${retrievedUser[0].id}  \n user: ${usersx.id}")


        assertEquals(retrievedUser[0].tvName,  user.tvName)



    }


    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList2() = runBlocking {
        //assert(true)

        // Given a user
        val user = UserProfileDetailsData(
            id = 1,
            tvName = "George Bluth",
            tvEmailID = "george.bluth@reqres.in",
            tvImageURl = "https://reqres.in/img/faces/1-image.jpg",
            profile = "Android Developer",
            yexp = "10"
        )

        val user2 = UserProfileDetailsData(
            id = 2,
            tvName = "George Bluth 2",
            tvEmailID = "george.bluth@reqres.in",
            tvImageURl = "https://reqres.in/img/faces/1-image.jpg",
            profile = "Android Developer2",
            yexp = "12"
        )

       val list = (mutableListOf <UserProfileDetailsData>())
        list.add(user)
        list.add(user2)

        // When inserting the user into the database
        userDao.insertUsers(list)

        // Then retrieving the user should return the same user
        val retrievedUser = userDao.getAllUsersList()


        assertEquals(retrievedUser.size,  list.size)

        assertEquals(retrievedUser[0].tvName,  list[0].tvName)

        assertEquals(retrievedUser[1].tvName,  list[1].tvName)
        assertNotSame(retrievedUser[0].tvName,  list[1].tvName)
    }

}