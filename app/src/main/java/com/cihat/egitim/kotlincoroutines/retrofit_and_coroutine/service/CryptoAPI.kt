package com.cihat.egitim.kotlincoroutines.retrofit_and_coroutine.service

import com.cihat.egitim.kotlincoroutines.retrofit_and_coroutine.model.CryptoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {
    @GET("atilsamancioglu/K21-JSONDataSet/refs/heads/master/crypto.json")
    suspend fun getData(): Response<List<CryptoModel>>

    @GET("atilsamancioglu/K21-JSONDataSet/refs/heads/master/crypto.json")
    fun getDataRx(): Observable<List<CryptoModel>>
}