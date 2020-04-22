@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if4055.lawancorona.network.provinsi.Attributes
import org.d3if4055.lawancorona.network.indonesia.DataIndonesia

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

fun Attributes.convertObjectProvinsiToDatabaseModel(): DataProvinsiDB {
    return DataProvinsiDB(
            FID = FID,
            Kode_Provi = Kode_Provi,
            Provinsi = Provinsi,
            Kasus_Posi = Kasus_Posi,
            Kasus_Semb = Kasus_Semb,
            Kasus_Meni = Kasus_Meni
        )
}