package org.marproject.lawancovid19.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.marproject.lawancovid19.database.DataIndonesiaDao
import org.marproject.lawancovid19.network.service.ApiCorona
import org.marproject.lawancovid19.utils.convertToDatabaseModel

@Suppress("SpellCheckingInspection")
class IndonesiaRepository (private  val indonesiaDao: DataIndonesiaDao) {

    val indonesia = indonesiaDao.getDataIndonesia()

    suspend fun refreshDataIndo() {
        withContext(Dispatchers.IO) {
            val miwok = ApiCorona.retrofitService.showData()
            Log.i("testingOnlineIndo", miwok.toString())
            indonesiaDao.insertAll(miwok.convertToDatabaseModel())
        }
    }
}