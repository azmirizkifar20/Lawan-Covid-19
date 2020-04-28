@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if4055.lawancorona.utils.DATA_GLOBAL
import org.d3if4055.lawancorona.utils.DATA_INDONESIA
import org.d3if4055.lawancorona.utils.DATA_PROVINSI

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