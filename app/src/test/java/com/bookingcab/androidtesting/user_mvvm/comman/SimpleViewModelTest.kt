package com.bookingcab.androidtesting.user_mvvm.comman

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class SimpleViewModelTest {

    // Rule to make LiveData work synchronously in tests
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    // Rule to enable Mockito annotations
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    // Mocking the Observer
    @Mock
    lateinit var observer: Observer<String>

    private val testDispatcher = StandardTestDispatcher()

    // Create an instance of SimpleViewModel with the test coroutine scope
    val viewModel = SimpleViewModel()



    @Before
    fun setUp() {
        // Set the TestCoroutineDispatcher as the main dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher to the original dispatcher
        Dispatchers.resetMain()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `test getLiveDataValue with True`() = runTest() {
        // Given
        val name = "John Doe"
        val age = 30

        // Observe the LiveData
        viewModel.myDetails.observeForever(observer)

        // When
        viewModel.getLiveDataValue(name, age)

        // Then
        // Advance time to execute the coroutine
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify that the LiveData has been updated with the expected value
        Mockito.verify(observer).onChanged("Hi Mr. John Doe, Your age is 30 and your so.....")

        // Clean up
        viewModel.myDetails.removeObserver(observer)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun `test getLiveDataValue`() = runBlockingTest {
        // Given
        val name = "John Doe"
        val age = 30

        // Use a CountDownLatch to wait for the LiveData value to be set
        val latch = CountDownLatch(1)

        // Custom observer to capture the LiveData value
        val customObserver = Observer<String> {
            // Perform assertions here
            assertEquals("Hi Mr. $name, Your age is $age and your so.....", it)
            latch.countDown()
        }

        // Observe the LiveData
        viewModel.myDetails.observeForever(customObserver)

        // When
        viewModel.getLiveDataValue(name, age)

        // Then
        // Wait for the value to be set
        latch.await(2, TimeUnit.SECONDS)

        // Clean up
        viewModel.myDetails.removeObserver(customObserver)
    }

}
