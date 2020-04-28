package org.d3if4055.lawancorona.ui.produktif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityBacaTutorialBinding
import org.d3if4055.lawancorona.utils.URL_MEDIUM

@Suppress("SpellCheckingInspection")
class BacaTutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBacaTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_baca_tutorial)

        // back button
        binding.back.setOnClickListener {
            startActivity(
                Intent(this, ProduktifActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                ))
        }

        // webview init
        val webView = binding.webview
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        // zoom support
        webView.settings.supportZoom()
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        // scroll support
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(URL_MEDIUM)
    }
}
