@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataProvinsiDao {
    @Query("SELECT * FROM data_provinsi")
    fun getDataProvinsi(): LiveData<List<DataProvinsiDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(provinsi: List<DataProvinsiDB>)
}