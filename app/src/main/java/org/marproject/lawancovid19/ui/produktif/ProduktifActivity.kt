package org.marproject.lawancovid19.ui.produktif

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.databinding.ActivityProduktifBinding
import org.marproject.lawancovid19.ui.menu.MenuActivity
import org.marproject.lawancovid19.utils.Constants.URL_GITHUB
import org.marproject.lawancovid19.utils.Constants.URL_LEARN_ANDROID
import org.marproject.lawancovid19.utils.Constants.URL_LEARN_WEB
import org.marproject.lawancovid19.utils.Constants.URL_PODCAST
import org.marproject.lawancovid19.utils.Constants.URL_TAUSIYAH
import org.marproject.lawancovid19.utils.Constants.URL_TOKOPEDIA

@Suppress("SpellCheckingInspection")
class ProduktifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProduktifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_produktif)

        initUI()
    }

    private fun initUI() {
        // back button
        binding.back.setOnClickListener {
            startActivity(
                Intent(this, MenuActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                ))
        }

        // belajar android
        binding.belajarAndroid.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_LEARN_ANDROID)
            startActivity(intent)
        }

        // belajar web
        binding.belajarWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_LEARN_WEB)
            startActivity(intent)
        }

        // baca tutorial
        binding.bacaTutorial.setOnClickListener {
            startActivity(Intent(this, BacaTutorialActivity::class.java))
        }

        // belanja online
        binding.belanjaOnline.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_TOKOPEDIA)
            startActivity(intent)
        }

        // denger tausiyah
        binding.kajian.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_TAUSIYAH)
            startActivity(intent)
        }

        // edukasi covid
        binding.edukasiCovid.setOnClickListener {
            startActivity(Intent(this, EdukasiCovidActivity::class.java))
        }

        // aktif github
        binding.github.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_GITHUB)
            startActivity(intent)
        }

        // baca quran
        binding.quran.setOnClickListener {
            startActivity(Intent(this, BacaQuranActivity::class.java))
        }

        // berita terkini
        binding.beritaTerkini.setOnClickListener {
            startActivity(Intent(this, BeritaTerkiniActivity::class.java))
        }

        // denger podcast
        binding.dengerPodcast.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_PODCAST)
            startActivity(intent)
        }
    }
}
