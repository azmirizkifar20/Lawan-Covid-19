package org.marproject.lawancovid19.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.databinding.ActivityMenuBinding
import org.marproject.lawancovid19.ui.about.AboutActivity
import org.marproject.lawancovid19.ui.produktif.ProduktifActivity
import org.marproject.lawancovid19.ui.global.GlobalActivity
import org.marproject.lawancovid19.ui.provinsi.ProvinsiActivity

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
