package org.d3if4055.lawancorona.ui.global

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityGlobalBinding
import org.d3if4055.lawancorona.ui.CoronaViewModel
import org.d3if4055.lawancorona.ui.menu.MenuActivity
import org.d3if4055.lawancorona.utils.getCurrentDateTime
import org.d3if4055.lawancorona.utils.toStringFormat

@Suppress("SpellCheckingInspection")
class GlobalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalBinding
    private lateinit var viewModel: CoronaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_global)

        // init viewmodel
        viewModel = ViewModelProvider(this).get(CoronaViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        // set current time
        val date = getCurrentDateTime()
        val dateInString = date.toStringFormat("EEEE, dd MMMM yyyy")
        binding.tvTanggal.text = dateInString

        // observe data global
        viewModel.dataGlobal.observe({ lifecycle }, { list ->
            Log.i("testingOfflineGlobal", list.toString())

            list.map {
                when(it.name) {
                    "Total Positif" -> binding.tvPositif.text = it.value
                    "Total Sembuh" -> binding.tvSembuh.text = it.value
                    "Total Meninggal" -> binding.tvMeninggal.text = it.value
                }
            }
        })

        viewModel.response.observe({ lifecycle }, {
            if (it.isNotEmpty() && it != "") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        // back button
        binding.back.setOnClickListener {
            startActivity(
                Intent(this, MenuActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                ))
        }

        // layanan bantuan
        binding.btnStatistik.setOnClickListener {
            startActivity(Intent(this, StatistikInterActivity::class.java))
        }

        binding.btnDiagnosa.setOnClickListener {
            startActivity(Intent(this, DiagnosaMandiriActivity::class.java))
        }
    }

}
