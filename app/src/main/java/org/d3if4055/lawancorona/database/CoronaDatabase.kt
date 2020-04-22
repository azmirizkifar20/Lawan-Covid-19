package org.d3if4055.lawancorona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("SpellCheckingInspection")
@Database(entities = [DataIndonesiaDB::class, DataProvinsiDB::class], version = 1, exportSchema = false)
abstract class CoronaDatabase : RoomDatabase() {

    abstract val dataIndonesiaDao: DataIndonesiaDao
    abstract val dataProvinsiDao: DataProvinsiDao

    companion object {
        @Volatile
        private var INSTANCE: CoronaDatabase? = null

        fun getInstance(context: Context): CoronaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoronaDatabase::class.java,
                        "corona_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}