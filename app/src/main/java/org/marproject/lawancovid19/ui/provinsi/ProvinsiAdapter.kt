package org.marproject.lawancovid19.ui.provinsi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.database.DataProvinsiDB
import org.marproject.lawancovid19.databinding.ItemProvinsiBinding
import java.util.*

// class ini tidak terpakai karena digantikan oleh reusable adapter
@Suppress("SpellCheckingInspection")
class ProvinsiAdapter : RecyclerView.Adapter<ProvinsiAdapter.ProvinsiViewHolder>(), Filterable {

    // list provinsi
    private var listProvinsi = mutableListOf<DataProvinsiDB>()

    // current list untuk menampung data yang akan di filter
    private var currentList = mutableListOf<DataProvinsiDB>()

    inner class ProvinsiViewHolder(
        val itemProvinsiBinding: ItemProvinsiBinding
    ) : RecyclerView.ViewHolder(itemProvinsiBinding.root)

    // get item gunakan current list karena akan menampilkan hasil filter
    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProvinsiViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_provinsi, parent, false)
    )

    // gunakan currentList karena ingin menampilkan hasil filter (apabila digunakan)
    override fun onBindViewHolder(holder: ProvinsiViewHolder, position: Int) {
        holder.itemProvinsiBinding.tvProvinsi.text = currentList[position].Provinsi
        holder.itemProvinsiBinding.tvPositif.text = currentList[position].Kasus_Posi.toString()
        holder.itemProvinsiBinding.tvSembuh.text = currentList[position].Kasus_Semb.toString()
        holder.itemProvinsiBinding.tvMeninggal.text = currentList[position].Kasus_Meni.toString()
    }

    fun addListProvinsi(provinsi: List<DataProvinsiDB>) {
        listProvinsi = provinsi as MutableList<DataProvinsiDB>
        // set currentList agar bisa dijadikan data awal
        currentList = listProvinsi
        notifyDataSetChanged()
    }

    // fungsi turunan dari class Filter
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // tampung hasil cari
                val cari = constraint?.toString()

                // cek apakah variabel cari null/tidak
                cari?.let {
                    // buat list untuk menampung data hasil pencarian
                    val resultList = mutableListOf<DataProvinsiDB>()
                    for (row in listProvinsi) {
                        // pencocokan data
                        if (row.Provinsi.toLowerCase(Locale.ROOT).contains(cari.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }

                    // set current list dengan hasil data pencarian
                    currentList = resultList
                } ?: run { // jika variabel cari = null
                    currentList = listProvinsi
                }

                // buat variabel filter untuk syarat nilai return
                val filterResults = FilterResults()
                filterResults.values = currentList

                return filterResults
            }

            @Suppress("SpellCheckingInspection", "UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currentList = results?.values as MutableList<DataProvinsiDB>
                notifyDataSetChanged() // untuk notice view agar data berubah sesuai pencarian
            }

        }
    }

}