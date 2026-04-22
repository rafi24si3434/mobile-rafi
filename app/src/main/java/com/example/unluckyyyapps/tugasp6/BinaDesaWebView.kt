package com.example.unluckyyyapps

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.unluckyyyapps.databinding.BinadesaWebviewBinding

class BinaDesaWebView : AppCompatActivity() {

    private lateinit var binding: BinadesaWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BinadesaWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.webViewClient = WebViewClient()

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true

        binding.webView.loadUrl(
            "https://rafii.alwaysdata.net"
        )
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}