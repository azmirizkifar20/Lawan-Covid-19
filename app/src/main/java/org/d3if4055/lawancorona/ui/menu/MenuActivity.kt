package org.d3if4055.lawancorona.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityMenuBinding
import org.d3if4055.lawancorona.ui.provinsi.ProvinsiActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)

        binding.btnProvinsi.setOnClickListener {
            startActivity(Intent(this, ProvinsiActivity::class.java))
        }

        binding.btnGlobal.setOnClickListener {
            val gmmIntentUri: Uri = Uri.parse("geo:41.8719,12.5674")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}
