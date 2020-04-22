package org.d3if4055.lawancorona.ui.provinsi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.database.DataProvinsiDB
import org.d3if4055.lawancorona.databinding.ItemProvinsiBinding

@Suppress("SpellCheckingInspection")
class ProvinsiAdapter : RecyclerView.Adapter<ProvinsiAdapter.ProvinsiViewHolder>() {

    // list provinsi
    private var listProvinsi = mutableListOf<DataProvinsiDB>()

    inner class ProvinsiViewHolder(
        val itemProvinsiBinding: ItemProvinsiBinding
    ) : RecyclerView.ViewHolder(itemProvinsiBinding.root)

    override fun getItemCount() = listProvinsi.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProvinsiViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_provinsi, parent, false)
    )

    override fun onBindViewHolder(holder: ProvinsiViewHolder, position: Int) {
        holder.itemProvinsiBinding.tvProvinsi.text = listProvinsi[position].Provinsi
        holder.itemProvinsiBinding.tvPositif.text = listProvinsi[position].Kasus_Posi.toString()
        holder.itemProvinsiBinding.tvSembuh.text = listProvinsi[position].Kasus_Semb.toString()
        holder.itemProvinsiBinding.tvMeninggal.text = listProvinsi[position].Kasus_Meni.toString()
    }

    fun addListProvinsi(provinsi: List<DataProvinsiDB>) {
        listProvinsi = provinsi as MutableList<DataProvinsiDB>
        notifyDataSetChanged()
    }

}