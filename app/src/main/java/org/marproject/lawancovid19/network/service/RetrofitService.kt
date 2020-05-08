@file:Suppress("SpellCheckingInspection")

package org.marproject.lawancovid19.network.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.marproject.lawancovid19.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

object ApiCorona {
    val retrofitService: ApiService = retrofit.create(ApiService::class.java)
}