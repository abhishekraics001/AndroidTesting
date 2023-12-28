package com.app.androidtesting.user_mvvm.comman

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineTest {

    suspend fun getSumOfTwoNoMainThread(n1: Int, n2: Int): Int {
       var sum = 0;
        GlobalScope.launch(Dispatchers.IO) {
            delay(200)
           sum =  n1 + n2
           delay(200)
        }.join()
        return sum
    }

    suspend fun getSumOfTwoNoDefaultThread(n1: Int, n2: Int): Int{
        var sum = 0;
        GlobalScope.launch (Dispatchers.Default) {
            delay(200)
            sum = n1 + n2
            delay(200)
        }.join()
        return sum
    }

    suspend fun getSumOfTwoNoIOThread(n1: Int, n2: Int): Int{
        var sum = 0
        GlobalScope.launch (Dispatchers.IO) {
            delay(200)
            sum = n1 + n2
            delay(200)
        }.join()
        return sum
    }
}