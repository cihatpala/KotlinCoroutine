package com.cihat.egitim.kotlincoroutines.retrofit_and_coroutine.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    @SerializedName(value = "currency")
    val currency: String,
    @SerializedName("price")
    val price: String
)