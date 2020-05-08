package org.marproject.lawancovid19.network.provinsi

@Suppress("SpellCheckingInspection")
data class ProvinsiAttributes (
    val FID: Int,
    val Kode_Provi: Int,
    val Provinsi: String,
    val Kasus_Posi: Int,
    val Kasus_Semb: Int,
    val Kasus_Meni: Int
)
