@file:Suppress("SpellCheckingInspection")

package org.marproject.lawancovid19.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.marproject.lawancovid19.utils.Constants.DATA_GLOBAL
import org.marproject.lawancovid19.utils.Constants.DATA_INDONESIA
import org.marproject.lawancovid19.utils.Constants.DATA_PROVINSI

@Entity(tableName = DATA_INDONESIA)
data class DataIndonesiaDB constructor (
    @PrimaryKey
    val name: String,
    val positif: String,
    val sembuh: String,
    val meninggal: String
)

@Entity(tableName = DATA_PROVINSI)
data class DataProvinsiDB constructor (
    @PrimaryKey
    val FID: Int,
    val Kode_Provi: Int,
    val Provinsi: String,
    val Kasus_Posi: Int,
    val Kasus_Semb: Int,
    val Kasus_Meni: Int
)

@Entity(tableName = DATA_GLOBAL)
data class DataGlobalDB constructor(
    @PrimaryKey
    val name: String,
    val value: String
)