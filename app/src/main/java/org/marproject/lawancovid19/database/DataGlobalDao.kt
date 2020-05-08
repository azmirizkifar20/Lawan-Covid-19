@file:Suppress("SpellCheckingInspection")

package org.marproject.lawancovid19.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataGlobalDao {
    @Query("SELECT * FROM data_global")
    fun getDataGlobal(): LiveData<List<DataGlobalDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCase(positif: DataGlobalDB)
}