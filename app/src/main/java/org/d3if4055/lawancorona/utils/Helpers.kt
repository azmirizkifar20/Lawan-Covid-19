@file:Suppress("SpellCheckingInspection")

package org.d3if4055.lawancorona.utils

import org.d3if4055.lawancorona.database.DataIndonesiaDB
import org.d3if4055.lawancorona.database.DataProvinsiDB
import org.d3if4055.lawancorona.database.DataGlobalDB
import org.d3if4055.lawancorona.network.global.DataGlobal
import org.d3if4055.lawancorona.network.indonesia.DataIndonesia
import org.d3if4055.lawancorona.network.provinsi.ProvinsiAttributes
import java.text.SimpleDateFormat
import java.util.*

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

fun ProvinsiAttributes.convertObjectProvinsiToDatabaseModel(): DataProvinsiDB {
    return DataProvinsiDB(
        FID = FID,
        Kode_Provi = Kode_Provi,
        Provinsi = Provinsi,
        Kasus_Posi = Kasus_Posi,
        Kasus_Semb = Kasus_Semb,
        Kasus_Meni = Kasus_Meni
    )
}

fun DataGlobal.convertObjectPositifToDatabaseModel(): DataGlobalDB {
    return DataGlobalDB(
        name = name,
        value = value
    )
}

fun Date.toStringFormat(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}