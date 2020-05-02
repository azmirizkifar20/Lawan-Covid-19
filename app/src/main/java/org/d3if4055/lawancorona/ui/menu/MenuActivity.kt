package org.d3if4055.lawancorona.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityMenuBinding
import org.d3if4055.lawancorona.ui.about.AboutActivity
import org.d3if4055.lawancorona.ui.produktif.ProduktifActivity
import org.d3if4055.lawancorona.ui.global.GlobalActivity
import org.d3if4055.lawancorona.ui.provinsi.ProvinsiActivity
import org.d3if4055.lawancorona.work.RefreshDataWorker
import java.util.concurrent.TimeUnit

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)

//        val task = OneTimeWorkRequest.Builder(RefreshDataWorker::class.java).build()
//        val workManager = WorkManager.getInstance()
//        workManager.enqueue(task)
//
//        workManager.getWorkInfoByIdLiveData(task.id).observe(this, Observer {
//            if (it != null) {
//                Log.i("testingWorker", "Status changed to : ${it.state.isFinished}")
//            }
//        })

        binding.btnProvinsi.setOnClickListener {
            startActivity(Intent(this, ProvinsiActivity::class.java))
        }

        binding.btnGlobal.setOnClickListener {
            startActivity(Intent(this, GlobalActivity::class.java))
        }

        binding.btnGrafik.setOnClickListener {
            startActivity(Intent(this, GrafikActivity::class.java))
        }

        binding.btnLearning.setOnClickListener {
            startActivity(Intent(this, ProduktifActivity::class.java))
        }

        binding.btnHotline.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:119")
            startActivity(intent)
        }

        binding.btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}
