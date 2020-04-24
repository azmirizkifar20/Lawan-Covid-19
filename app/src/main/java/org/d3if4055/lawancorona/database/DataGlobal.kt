package org.d3if4055.lawancorona.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataGlobal {
    @Query("SELECT * FROM data_global")
    fun getDataGlobal(): LiveData<List<DataGlobalDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCase(positif: DataGlobalDB)
}