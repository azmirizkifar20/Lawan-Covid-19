@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.network.service

import org.d3if4055.lawancorona.network.global.DataGlobal
import org.d3if4055.lawancorona.network.indonesia.DataIndonesia
import org.d3if4055.lawancorona.network.provinsi.DataProvinsi
import org.d3if4055.lawancorona.utils.Constants.GET_INDONESIA
import org.d3if4055.lawancorona.utils.Constants.GET_MENINGGAL
import org.d3if4055.lawancorona.utils.Constants.GET_POSITIF
import org.d3if4055.lawancorona.utils.Constants.GET_PROVINSI
import org.d3if4055.lawancorona.utils.Constants.GET_SEMBUH
import retrofit2.http.GET

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