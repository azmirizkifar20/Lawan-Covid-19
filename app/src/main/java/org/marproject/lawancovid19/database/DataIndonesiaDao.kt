package org.marproject.lawancovid19.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataIndonesiaDao {

    @Query("SELECT * FROM data_indonesia")
    fun getDataIndonesia(): LiveData<List<DataIndonesiaDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(indonesia: List<DataIndonesiaDB>)
}