package org.marproject.lawancovid19.ui.provinsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_provinsi.view.*
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.database.DataProvinsiDB
import org.marproject.lawancovid19.databinding.ActivityProvinsiBinding
import org.marproject.lawancovid19.ui.CoronaViewModel
import org.marproject.lawancovid19.ui.menu.MenuActivity
import org.marproject.lawancovid19.utils.Constants.SEARCH_HINT
import org.marproject.reusablerecyclerviewadapter.ReusableAdapter
import org.marproject.reusablerecyclerviewadapter.interfaces.AdapterCallback

@Suppress("SpellCheckingInspection")
class ProvinsiActivity : AppCompatActivity() {

    // buat binding dan viewmodel
    private lateinit var binding: ActivityProvinsiBinding
    private lateinit var viewModel: CoronaViewModel

    // buat adapter
    private lateinit var adapter: ReusableAdapter<DataProvinsiDB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_provinsi)

        // init viewmodel
        viewModel = ViewModelProvider(this).get(CoronaViewModel::class.java)

        // init adapter
        adapter = ReusableAdapter(this)

        // init material searchbar
        val searchBar = binding.searchBar
        searchBar.setHint(SEARCH_HINT)
        searchBar.setPlaceHolder(SEARCH_HINT)
        searchBar.setSearchIcon(R.drawable.ic_search)

        // searchbar event
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            // event ketika text berubah/diketik
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

        })

        initUI()
    }

    private fun initUI() {
        // observe data dari room db
        viewModel.dataIndo.observe({ lifecycle }, { list ->
            Log.i("testingOfflineIndo", list.toString())
            list.map {
                binding.tvPositif.text = it.positif
                binding.tvSembuh.text = it.sembuh
                binding.tvMeninggal.text = it.meninggal
            }
        })

        // observe data provinsi dari room db
        viewModel.dataProv.observe({ lifecycle }, {
            Log.i("testingOfflineProv", it.toString())
            if (it.isNotEmpty()) {
                Log.i("testing", "pengguna lama")
                setupAdapter(binding.rvProvinsi, it.sortedByDescending { data -> data.Kasus_Posi })
            } else {
                Log.i("testing", "pengguna baru")
                setupAdapter(binding.rvProvinsi, it.sortedByDescending { data -> data.Kasus_Posi })
            }
        })

        // observe respon
        viewModel.response.observe({ lifecycle }, {
            if (it.isNotEmpty() && it != "") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        binding.back.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            ))
        }
    }

    private fun setupAdapter(recyclerView: RecyclerView, items: List<DataProvinsiDB>) {
        adapter.adapterCallback(adapterCallback)
            .setLayout(R.layout.item_provinsi)
            .filterable()
            .addData(items)
            .isVerticalView()
            .build(recyclerView)
    }

    private val adapterCallback = object : AdapterCallback<DataProvinsiDB> {
        override fun initComponent(itemView: View, data: DataProvinsiDB, itemIndex: Int) {
            itemView.tv_provinsi.text = data.Provinsi
            itemView.tv_positif.text = data.Kasus_Posi.toString()
            itemView.tv_sembuh.text = data.Kasus_Semb.toString()
            itemView.tv_meninggal.text = data.Kasus_Meni.toString()
        }

        override fun onItemClicked(itemView: View, data: DataProvinsiDB, itemIndex: Int) { }

    }
}
