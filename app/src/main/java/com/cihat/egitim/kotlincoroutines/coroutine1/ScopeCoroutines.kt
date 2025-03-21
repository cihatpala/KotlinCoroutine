package com.cihat.egitim.kotlincoroutines.coroutine1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    runBlocking {
//        launch {
//            delay(5000)
//            println("runBlocking")
//        }
//
//        coroutineScope {
//            launch {
//                delay(3000)
//                println("coroutineScope")
//            }
//        }
//    }

    //Dispatcher
    //Dispatchers.Default -> CPU Intensive
    //Dispatchers.IO -> Input/Output (network processing)
    //Dispatchers.Main -> UI
    //Dispatchers.Unconfined -> Inhereted (içersinde kullanılana göre kendini ayarlayan context)
    runBlocking {
        launch(Dispatchers.Main) {
            println("Main Thread: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("IO Thread: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default Thread: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined Thread: ${Thread.currentThread().name}")
        }
    }
}