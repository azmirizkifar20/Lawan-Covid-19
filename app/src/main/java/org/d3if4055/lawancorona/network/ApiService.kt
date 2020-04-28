@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4055.lawancorona.network.global.DataGlobal
import org.d3if4055.lawancorona.network.indonesia.DataIndonesia
import org.d3if4055.lawancorona.network.provinsi.DataProvinsi
import org.d3if4055.lawancorona.utils.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// API Data
interface ApiService {

    @GET(GET_INDONESIA)
    suspend fun showData(): List<DataIndonesia>

    @GET(GET_PROVINSI)
    suspend fun showDataProvinsi(): List<DataProvinsi>

    @GET(GET_POSITIF)
    suspend fun showPositifCase(): DataGlobal

    @GET(GET_SEMBUH)
    suspend fun showSembuhCase(): DataGlobal

    @GET(GET_MENINGGAL)
    suspend fun showMeninggalCase(): DataGlobal
}

object ApiCorona {
    val retrofitService = retrofit.create(ApiService::class.java)
}