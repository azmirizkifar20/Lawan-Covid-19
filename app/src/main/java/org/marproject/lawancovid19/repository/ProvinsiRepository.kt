package org.marproject.lawancovid19.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.marproject.lawancovid19.database.DataProvinsiDB
import org.marproject.lawancovid19.database.DataProvinsiDao
import org.marproject.lawancovid19.network.service.ApiCorona
import org.marproject.lawancovid19.utils.convertObjectProvinsiToDatabaseModel

@Suppress("SpellCheckingInspection")
class ProvinsiRepository(private val provinsiDao: DataProvinsiDao) {

    val provinsi = provinsiDao.getDataProvinsi()

    suspend fun refreshDataProv() {
        withContext(Dispatchers.IO) {
            val listProvinsi = mutableListOf<DataProvinsiDB>()
            val dataProvinsi = ApiCorona.retrofitService.showDataProvinsi()

            dataProvinsi.map {
                listProvinsi.add(it.attributes.convertObjectProvinsiToDatabaseModel())
                Log.i("testingOnlineProv", it.attributes.convertObjectProvinsiToDatabaseModel().toString())
            }

            provinsiDao.insertAll(listProvinsi)
        }
    }
}