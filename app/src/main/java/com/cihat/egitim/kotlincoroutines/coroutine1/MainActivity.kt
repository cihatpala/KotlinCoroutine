package com.cihat.egitim.kotlincoroutines.coroutine1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cihat.egitim.kotlincoroutines.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Scope
        //GlobalScope, runBlocking, CoroutineScope

        /*
        GlobalScope.launch {
             repeat(100_000) {
                launch {
                    println("Android")
                }
             }
        }

        */
        /*
        //runBlocking
        println("before run blocking")
        runBlocking {
            launch {
                delay(10000)
                println("run blocking")
            }
        }
        println("after run blocking")
         */

        /*
        //CoroutineScope
        //Context -> nerede çalıştırıldığı coroutine'in hangi verilerle çalışacağı
        println("before coroitinescope")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("in coroutinescope")
        }
        println("after coroutinescope")
         */

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
}