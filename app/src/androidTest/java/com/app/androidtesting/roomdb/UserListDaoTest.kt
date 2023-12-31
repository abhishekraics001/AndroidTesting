package com.app.androidtesting.roomdb

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.androidtesting.roomDB.AppDatabase
import com.app.androidtesting.roomDB.UserListDao
import com.app.androidtesting.roomDB.userlistVM.UserProfileDetailsData
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListDaoTest {



    private lateinit var userDao: UserListDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        userDao = db.userDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndReadUser() {
       // val user = User(name = "John Doe", age = 25)
        val user1 =  UserProfileDetailsData(tvName = "George Bluth", tvEmailID = "george.bluth@reqres.in", tvImageURl = "https://reqres.in/img/faces/1-image.jpg", profile = "Android Developer", yexp = "10")
        val user2 = UserProfileDetailsData(tvName ="Janet Weaver", tvEmailID ="janet.weaver@reqres.in", tvImageURl ="https://reqres.in/img/faces/2-image.jpg", profile ="IOS Developer", yexp ="9")


    /*    userDao.insertUser(user1)

        val retrievedUser = userDao.getUserById(user.id)
        assertNotNull(retrievedUser)
        assertEquals(user.name, retrievedUser?.name)
        assertEquals(user.age, retrievedUser?.age)*/
    }
}