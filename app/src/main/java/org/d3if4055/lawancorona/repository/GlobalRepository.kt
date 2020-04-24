package org.d3if4055.lawancorona.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4055.lawancorona.database.DataGlobal
import org.d3if4055.lawancorona.network.ApiCorona
import org.d3if4055.lawancorona.utils.convertObjectPositifToDatabaseModel

class GlobalRepository(private val global: DataGlobal) {

    val dataGlobal = global.getDataGlobal()

    suspend fun refreshDataPositif() {
        withContext(Dispatchers.IO) {
            val positif = ApiCorona.retrofitService.showPositifCase()
            val sembuh = ApiCorona.retrofitService.showSembuhCase()
            val meninggal = ApiCorona.retrofitService.showMeninggalCase()

            Log.i("testingOnlineGlobal", "berhasil!")
            global.insertCase(positif.convertObjectPositifToDatabaseModel())
            global.insertCase(sembuh.convertObjectPositifToDatabaseModel())
            global.insertCase(meninggal.convertObjectPositifToDatabaseModel())
        }
    }
}