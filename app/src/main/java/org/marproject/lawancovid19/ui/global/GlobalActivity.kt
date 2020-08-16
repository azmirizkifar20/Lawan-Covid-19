package org.marproject.lawancovid19.ui.global

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.databinding.ActivityGlobalBinding
import org.marproject.lawancovid19.ui.CoronaViewModel
import org.marproject.lawancovid19.utils.Constants.DATE_PATTERN
import org.marproject.lawancovid19.utils.getCurrentDateTime
import org.marproject.lawancovid19.utils.toStringFormat

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
        val dateInString = date.toStringFormat(DATE_PATTERN)
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
        binding.back.setOnClickListener { finish() }

        // layanan bantuan
        binding.btnStatistik.setOnClickListener {
            startActivity(Intent(this, StatistikInterActivity::class.java))
        }

        binding.btnDiagnosa.setOnClickListener {
            startActivity(Intent(this, DiagnosaMandiriActivity::class.java))
        }
    }

}
