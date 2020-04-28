package org.d3if4055.lawancorona.ui.produktif

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityProduktifBinding
import org.d3if4055.lawancorona.ui.menu.MenuActivity

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
            intent.data = Uri.parse("https://www.youtube.com/user/SimplifiedCoding")
            startActivity(intent)
        }

        // belajar web
        binding.belajarWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/channel/UCkXmLjEr95LVtGuIm3l2dPg")
            startActivity(intent)
        }

        // baca tutorial
        binding.bacaTutorial.setOnClickListener {
            startActivity(Intent(this, BacaTutorialActivity::class.java))
        }

        // belanja online
        binding.belanjaOnline.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.tokopedia.com/")
            startActivity(intent)
        }

        // denger tausiyah
        binding.kajian.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/channel/UCVes0G5DqPa3ZHPL4W2OrhA")
            startActivity(intent)
        }

        // edukasi covid
        binding.edukasiCovid.setOnClickListener {
            startActivity(Intent(this, EdukasiCovidActivity::class.java))
        }

        // aktif github
        binding.github.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/azmirizkifar20")
            startActivity(intent)
        }

        // baca quran
        binding.quran.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://bl.app.link/BukaQuran")
            startActivity(intent)
        }
    }
}
