package org.d3if4055.lawancorona.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4055.lawancorona.database.DataProvinsiDB
import org.d3if4055.lawancorona.database.DataProvinsiDao
import org.d3if4055.lawancorona.network.ApiCorona
import org.d3if4055.lawancorona.utils.convertObjectProvinsiToDatabaseModel

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