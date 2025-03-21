package com.cihat.egitim.kotlincoroutines.retrofit_and_coroutine.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cihat.egitim.kotlincoroutines.R
import com.cihat.egitim.kotlincoroutines.databinding.ActivityRetrofitAndCoroutineBinding
import com.cihat.egitim.kotlincoroutines.retrofit_and_coroutine.model.CryptoModel
import com.cihat.egitim.kotlincoroutines.retrofit_and_coroutine.service.CryptoAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitAndCoroutineActivity : AppCompatActivity() {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModel: ArrayList<CryptoModel>? = null
    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: ActivityRetrofitAndCoroutineBinding

    //Coroutine using
    private var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRetrofitAndCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        loadDataWithRxJava()
        loadDataWithCoroutine()
    }

    fun loadDataWithCoroutine() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getData()
            withContext(Dispatchers.Main + exceptionHandler) {
//                throw throw IOException("Coroutine Exception Handler Testing") //-> exceptionHandler testing
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModel = ArrayList(it)
                        var totalString = ""
                        for (model: CryptoModel in cryptoModel!!) {
                            println("model currency: ${model.currency}")
                            println("model price: ${model.price}")
                            totalString += "Currency:${model.currency} - Price:${model.price}\n"
                        }
                        binding.text.suffixText = totalString
                    }
                }
            }
        }
    }


    fun loadDataWithRxJava() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable.add(retrofit.getDataRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleResponse, ::handleError)
        )
    }

    private fun handleError(error: Throwable) {
        println("RetrofitError Error: ${error.message}")
    }

    private fun handleResponse(list: List<CryptoModel>) {
        cryptoModel = ArrayList(list)
        var totalString = ""
        for (model: CryptoModel in cryptoModel!!) {
            println("model currency: ${model.currency}")
            println("model price: ${model.price}")
            totalString += "Currency:${model.currency} - Price:${model.price}\n"
        }
        binding.text.suffixText = totalString
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        job?.cancel()
    }
}