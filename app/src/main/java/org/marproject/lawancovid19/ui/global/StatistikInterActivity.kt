package org.marproject.lawancovid19.ui.global

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.databinding.ActivityStatistikInterBinding
import org.marproject.lawancovid19.utils.Constants
import org.marproject.lawancovid19.utils.Constants.URL_STATISTIK_GLOBAL


@Suppress("SpellCheckingInspection")
class StatistikInterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatistikInterBinding
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statistik_inter)

        // back button
        binding.back.setOnClickListener { finish() }

        // webview init
        // webview init
        webView = binding.webview
        webView.apply {
            settings.loadsImagesAutomatically = true
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true

            // zoom support
            settings.supportZoom()
            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            // scroll support
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = WebViewClient()
            loadUrl(URL_STATISTIK_GLOBAL)

            // loading handler
            webViewClient = loadingHandler
            webToolbar()
        }
    }

    private fun webToolbar() {
        binding.backward.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
                binding.loading.visibility = View.VISIBLE
            }
        }

        binding.refresh.setOnClickListener {
            webView.reload()
            binding.loading.visibility = View.VISIBLE
        }

        binding.forward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
                binding.loading.visibility = View.VISIBLE
            }
        }
    }

    private val loadingHandler = object : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            binding.loading.visibility = View.GONE
            super.onPageStarted(view, url, favicon)
        }

    }

    // back stack on webview
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        finish()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
