package com.cihat.egitim.kotlincoroutines.coroutine1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("Context1: $coroutineContext")
            withContext(Dispatchers.IO) {
                println("Context2: $coroutineContext")
            }
        }
    }
}