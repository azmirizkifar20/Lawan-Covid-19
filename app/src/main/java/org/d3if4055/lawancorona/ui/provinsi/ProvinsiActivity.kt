package org.d3if4055.lawancorona.ui.provinsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityProvinsiBinding
import org.d3if4055.lawancorona.ui.CoronaViewModel
import org.d3if4055.lawancorona.ui.menu.MenuActivity

@Suppress("SpellCheckingInspection")
class ProvinsiActivity : AppCompatActivity() {

    // buat binding dan viewmodel
    private lateinit var binding: ActivityProvinsiBinding
    private lateinit var viewModel: CoronaViewModel

    // buat adapter
    private val provinsiAdapter = ProvinsiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_provinsi)

        // init viewmodel
        viewModel = ViewModelProvider(this).get(CoronaViewModel::class.java)

        // set recyclerview
        val recyclerView = binding.rvProvinsi
        recyclerView.apply {
            this.adapter = provinsiAdapter
            this.layoutManager = LinearLayoutManager(this@ProvinsiActivity)
        }

        val searchBar = binding.searchBar
        searchBar.setHint("Cari provinsi")
        searchBar.setPlaceHolder("Cari provinsi")
        searchBar.setSpeechMode(true)

        initUI()
    }

    private fun initUI() {
        // observe data dari local db
        viewModel.dataIndo.observe({ lifecycle }, { list ->
            Log.i("testingOfflineIndo", list.toString())
            list.map {
                binding.tvPositif.text = it.positif
                binding.tvSembuh.text = it.sembuh
                binding.tvMeninggal.text = it.meninggal
            }
        })

        viewModel.dataProv.observe({ lifecycle }, {
            Log.i("testingOfflineProv", it.toString())
            if (it.isNotEmpty()) {
                Log.i("testing", "pengguna lama")
                provinsiAdapter.addListProvinsi(it.sortedByDescending { data -> data.Kasus_Posi })
            } else {
                Log.i("testing", "pengguna baru")
                provinsiAdapter.addListProvinsi(it)
            }
        })

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
}
