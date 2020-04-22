@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if4055.lawancorona.network.DataIndonesia

@Entity(tableName = "data_indonesia")
data class DataIndonesiaDB constructor (
    @PrimaryKey
    val name: String,
    val positif: String,
    val sembuh: String,
    val meninggal: String
)

@Entity(tableName = "data_provinsi")
data class DataProvinsiDB constructor(
    @PrimaryKey
    val FID: Int,
    val Kode_Provi: Int,
    val Provinsi: String,
    val Kasus_Posi: Int,
    val Kasus_Semb: Int,
    val Kasus_Meni: Int
)

fun List<DataIndonesiaDB>.convertToNetworkModel(): List<DataIndonesia> {
    return map {
        DataIndonesia(
            name = it.name,
            positif = it.positif,
            sembuh = it.sembuh,
            meninggal = it.meninggal
        )
    }
}

fun List<DataIndonesia>.convertToDatabaseModel(): List<DataIndonesiaDB> {
    return map {
        DataIndonesiaDB(
            name = it.name,
            positif = it.positif,
            sembuh = it.sembuh,
            meninggal = it.meninggal
        )
    }
}