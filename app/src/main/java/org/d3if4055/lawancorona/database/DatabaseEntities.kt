@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_indonesia")
data class DataIndonesiaDB constructor (
    @PrimaryKey
    val name: String,
    val positif: String,
    val sembuh: String,
    val meninggal: String
)

@Entity(tableName = "data_provinsi")
data class DataProvinsiDB constructor (
    @PrimaryKey
    val FID: Int,
    val Kode_Provi: Int,
    val Provinsi: String,
    val Kasus_Posi: Int,
    val Kasus_Semb: Int,
    val Kasus_Meni: Int
)

@Entity(tableName = "data_global")
data class DataGlobalDB constructor(
    @PrimaryKey
    val name: String,
    val value: String
)