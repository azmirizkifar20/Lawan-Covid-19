package org.marproject.lawancovid19.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.marproject.lawancovid19.database.DataGlobalDao
import org.marproject.lawancovid19.network.service.ApiCorona
import org.marproject.lawancovid19.utils.convertObjectPositifToDatabaseModel

@Suppress("SpellCheckingInspection")
class GlobalRepository(private val globalDao: DataGlobalDao) {

    val dataGlobal = globalDao.getDataGlobal()

    suspend fun refreshDataPositif() {
        withContext(Dispatchers.IO) {
            val positif = ApiCorona.retrofitService.showPositifCase()
            val sembuh = ApiCorona.retrofitService.showSembuhCase()
            val meninggal = ApiCorona.retrofitService.showMeninggalCase()

            Log.i("testingOnlineGlobal", "berhasil!")
            globalDao.insertCase(positif.convertObjectPositifToDatabaseModel())
            globalDao.insertCase(sembuh.convertObjectPositifToDatabaseModel())
            globalDao.insertCase(meninggal.convertObjectPositifToDatabaseModel())
        }
    }
}