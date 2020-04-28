package org.d3if4055.lawancorona.ui.global

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.d3if4055.lawancorona.R
import org.d3if4055.lawancorona.databinding.ActivityStatistikInterBinding
import org.d3if4055.lawancorona.utils.URL_STATISTIK_GLOBAL


@Suppress("SpellCheckingInspection")
class StatistikInterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatistikInterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statistik_inter)

        // back button
        binding.back.setOnClickListener {
            startActivity(
                Intent(this, GlobalActivity::class.java).addFlags(
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
        webView.loadUrl(URL_STATISTIK_GLOBAL)
    }
}
