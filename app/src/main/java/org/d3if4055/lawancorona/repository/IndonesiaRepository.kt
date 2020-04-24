package org.d3if4055.lawancorona.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4055.lawancorona.database.DataIndonesiaDao
import org.d3if4055.lawancorona.network.ApiCorona
import org.d3if4055.lawancorona.utils.convertToDatabaseModel

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