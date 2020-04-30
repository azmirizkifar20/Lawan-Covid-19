package org.d3if4055.lawancorona.ui.produktif

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityEdukasiCovidBinding
import org.d3if4055.lawancorona.utils.Constants.URL_EDUKASI_COVID

@Suppress("SpellCheckingInspection")
class EdukasiCovidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEdukasiCovidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edukasi_covid)

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
        webView.loadUrl(URL_EDUKASI_COVID)

        webView.webViewClient = loadingHandler
    }

    private val loadingHandler = object : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            binding.loading.visibility = View.GONE
            super.onPageStarted(view, url, favicon)
        }

    }
}
