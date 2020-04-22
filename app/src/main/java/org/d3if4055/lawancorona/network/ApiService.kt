@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.kawalcorona.com/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// API Data
interface ApiService {
    @GET("indonesia")
    suspend fun showData(): List<DataIndonesia>

    @GET("indonesia/provinsi")
    suspend fun showDataProvinsi(): List<DataProvinsi>
}

object ApiCorona {
    val retrofitService = retrofit.create(ApiService::class.java)
}