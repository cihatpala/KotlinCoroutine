package com.cihat.egitim.kotlincoroutines.coroutine1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val myJob = launch {
            delay(2000)
            println("launch running")
        }

        myJob.invokeOnCompletion {
            println("myJob end")
        }
    }
}