package com.cihat.egitim.kotlincoroutines.coroutine_exception_handling

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope

import com.cihat.egitim.kotlincoroutines.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CoroutineExHanActivity : AppCompatActivity() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("exceptionHandler error: ${throwable.localizedMessage}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutine_ex_han)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch(exceptionHandler) {
            supervisorScope {
                launch(exceptionHandler) {
                    throw Exception("launch1 Exception")
                }

                launch(exceptionHandler) {
                    delay(3000)
                    println("launch2 executed")
                }
            }
        }
    }
}