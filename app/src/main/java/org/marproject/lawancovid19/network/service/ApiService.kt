@file:Suppress("SpellCheckingInspection")

package org.marproject.lawancovid19.network.service

import org.marproject.lawancovid19.network.global.DataGlobal
import org.marproject.lawancovid19.network.indonesia.DataIndonesia
import org.marproject.lawancovid19.network.provinsi.DataProvinsi
import org.marproject.lawancovid19.utils.Constants.GET_INDONESIA
import org.marproject.lawancovid19.utils.Constants.GET_MENINGGAL
import org.marproject.lawancovid19.utils.Constants.GET_POSITIF
import org.marproject.lawancovid19.utils.Constants.GET_PROVINSI
import org.marproject.lawancovid19.utils.Constants.GET_SEMBUH
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