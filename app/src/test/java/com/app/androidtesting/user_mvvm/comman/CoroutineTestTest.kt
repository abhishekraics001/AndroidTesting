package com.app.androidtesting.user_mvvm.comman

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*


import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
class CoroutineTestTest {

    /** Note:
     * use the runBlocking or runTest scope to run & execute the test case
     * Create the Object of Coroutine Dispatcher TestCoroutineDispatcher and
     * set the  Coroutine Dispatcher on main  Thread  in @Before method
     * and  reset it in@After method
     *
     */

    private val testDispatcher = StandardTestDispatcher()

    // Replace GlobalScope with TestCoroutineScope
    private val coroutineTest = CoroutineTest()

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


    @Test
    fun `test getSumOfTwoNoMainThread True with testRun`() = runTest {
        // Given
       // val coroutineTest = CoroutineTest()

        // When
        val result = coroutineTest.getSumOfTwoNoMainThread(3, 4)

        // Then
        assertEquals(7, result)
    }

    @Test
    fun `test getSumOfTwoNoMainThread True`() = runBlocking {
        // Given
        // val coroutineTest = CoroutineTest()

        // When
        val result = coroutineTest.getSumOfTwoNoMainThread(3, 4)

        // Then
        assertEquals(7, result)
    }



    @Test
    fun `test getSumOfTwoNoMainThread False`() = runBlocking {
        // Given
        // val coroutineTest = CoroutineTest()

        // When
        val result = coroutineTest.getSumOfTwoNoMainThread(3, 4)

        // Then
        assertNotEquals(0, result)
    }


    @Test
    fun `test getSumOfTwoNoMainThread Faile`() = runBlocking {
        // Given
        // val coroutineTest = CoroutineTest()

        // When
        val result = coroutineTest.getSumOfTwoNoMainThread("hgsd".toInt(), 4)

        // Then
        assertNotEquals(0, result)
    }


    @Test
    fun `test getSumOfTwoNoDefaultThread`() = runBlocking {
        // Given
        //val coroutineTest = CoroutineTest()

        // When
        val result = coroutineTest.getSumOfTwoNoDefaultThread(3, 4)

        // Then
        assertEquals(7, result)
    }

    @Test
    fun `test getSumOfTwoNoIOThread`() = runBlocking {
        // Given
        //val coroutineTest = CoroutineTest()

        // When
        val result = coroutineTest.getSumOfTwoNoIOThread(3, 4)

        // Then
        assertEquals(7, result)
    }
}

