package com.app.androidtesting.roomdb

import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.app.androidtesting.roomDB.AppDatabase
import com.app.androidtesting.roomDB.UserListDao
import com.app.androidtesting.roomDB.userlistVM.UserProfileDetailsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class UserListDaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var userDao: UserListDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        userDao = db.userDao()
    }

    @After
    fun tearDown() {
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    fun testInsertAndReadUser() = runTest {
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
/*
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.androidtesting.roomDB.AppDatabase
import com.app.androidtesting.roomDB.UserListDao
import com.app.androidtesting.roomDB.userlistVM.UserListData
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
        val context = ApplicationProvider.getApplicationContext<Context>()
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
    suspend fun testInsertAndReadUser() {
      val user1 =  UserListData(tvName = "George Bluth", tvEmailID = "george.bluth@reqres.in", tvImageURl = "https://reqres.in/img/faces/1-image.jpg", profile = "Android Developer", yexp = "10")
        val user2 = UserListData(tvName ="Janet Weaver", tvEmailID ="janet.weaver@reqres.in", tvImageURl ="https://reqres.in/img/faces/2-image.jpg", profile ="IOS Developer", yexp ="9")


       // val user = User(name = "John Doe", age = 25)
        userDao.insertUser(user1)

       */
/* val retrievedUser = userDao.getUserById(user.id)
        assertNotNull(retrievedUser)
        assertEquals(user.name, retrievedUser?.name)
        assertEquals(user.age, retrievedUser?.age)*//*

    }
}*/
