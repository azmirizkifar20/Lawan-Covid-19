package org.marproject.lawancovid19.ui.produktif

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
import org.marproject.lawancovid19.databinding.ActivityBeritaTerkiniBinding
import org.marproject.lawancovid19.utils.Constants


@Suppress("SpellCheckingInspection")
class BeritaTerkiniActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeritaTerkiniBinding
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_berita_terkini)

        // back button
        binding.back.setOnClickListener {
            startActivity(
                Intent(this, ProduktifActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                ))
        }

        // webview init
        webView = binding.webview
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
        webView.loadUrl(Constants.URL_BERITA)

        webToolbar()
        webView.webViewClient = loadingHandler
    }

    private fun webToolbar() {
        binding.backward.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }

        binding.refresh.setOnClickListener {
            webView.reload()
            binding.loading.visibility = View.VISIBLE
        }

        binding.forward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
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
