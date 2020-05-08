package org.marproject.lawancovid19.ui.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.databinding.ActivityAboutBinding
import org.marproject.lawancovid19.ui.menu.MenuActivity

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)

        // back button
        binding.back.setOnClickListener {
            startActivity(
                Intent(this, MenuActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                ))
        }
    }
}
