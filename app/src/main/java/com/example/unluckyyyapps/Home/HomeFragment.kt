package com.example.unluckyyyapps.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unluckyyyapps.BinaDesa
import com.example.unluckyyyapps.BinaDesaWebView
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.SplashScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

// Perbaikan Import menyesuaikan struktur folder di project Anda
import com.example.unluckyyyapps.pertemuan_2.SecondActivity
import com.example.unluckyyyapps.pertemuan_3.ThirdActivity
import com.example.unluckyyyapps.pertemuan_4.FourthActivity
import com.example.unluckyyyapps.pertemuan_5.FifthActivity
import com.example.unluckyyyapps.pertemuan_7.SeventhActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TAMPILKAN NAMA DARI SHAREDPREFERENCES
        val sharedPref = requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
        val nama = sharedPref.getString("nama", null) ?: sharedPref.getString("loginUsername", "Rafi")
        view.findViewById<TextView>(R.id.tvGreeting)?.text = "Halo, $nama! 👋"

        // =========================
        // SETUP NEWS RECYCLERVIEW
        // =========================

        newsAdapter = NewsAdapter()
        val rvNews = view.findViewById<RecyclerView>(R.id.rvNews)
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = newsAdapter

        // Fetch berita dari API publik
        fetchNews()

        // =========================
        // PERTEMUAN
        // =========================

        view.findViewById<View>(R.id.btnPertemuan2).setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        view.findViewById<View>(R.id.btnPertemuan3).setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        view.findViewById<View>(R.id.btnPertemuan4).setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        view.findViewById<View>(R.id.btnPertemuan5).setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        view.findViewById<View>(R.id.btnPertemuan7).setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // =========================
        // TUGAS P6 - BINA DESA
        // =========================

        view.findViewById<View>(R.id.btnTugasP6).setOnClickListener {
            startActivity(Intent(requireContext(), BinaDesa::class.java))
        }

        view.findViewById<View>(R.id.btnWebTugasP6).setOnClickListener {
            startActivity(Intent(requireContext(), BinaDesaWebView::class.java))
        }

        // =========================
        // LOGOUT
        // =========================

        view.findViewById<View>(R.id.btnLogout).setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin keluar akun?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()

                    val sharedPref = requireContext().getSharedPreferences(
                        "user_pref",
                        android.content.Context.MODE_PRIVATE
                    )
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireContext(), SplashScreenActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun fetchNews() {
        lifecycleScope.launch {
            try {
                val newsList = withContext(Dispatchers.IO) {
                    val url = URL("https://saurav.tech/NewsAPI/top-headlines/category/general/us.json")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connectTimeout = 10000
                    connection.readTimeout = 10000

                    val response = connection.inputStream.bufferedReader().readText()
                    connection.disconnect()

                    val jsonObject = JSONObject(response)
                    val articles = jsonObject.getJSONArray("articles")

                    val list = mutableListOf<News>()
                    // Ambil maksimal 5 berita untuk ditampilkan
                    val limit = minOf(articles.length(), 5)

                    for (i in 0 until limit) {
                        val article = articles.getJSONObject(i)
                        val source = article.getJSONObject("source")

                        val title = article.optString("title", "")
                        val description = article.optString("description", "")
                        val urlToImage = article.optString("urlToImage", null)
                        val articleUrl = article.optString("url", "")
                        val publishedAt = article.optString("publishedAt", "")
                        val sourceName = source.optString("name", "Unknown")

                        // Skip berita yang tidak memiliki judul
                        if (title.isNotEmpty() && title != "null") {
                            list.add(
                                News(
                                    title = title,
                                    description = if (description == "null") null else description,
                                    urlToImage = if (urlToImage == "null") null else urlToImage,
                                    url = articleUrl,
                                    publishedAt = publishedAt,
                                    sourceName = sourceName
                                )
                            )
                        }
                    }

                    list
                }

                // Update RecyclerView di main thread
                newsAdapter.submitList(newsList)

            } catch (e: Exception) {
                Log.e("HomeFragment", "Gagal memuat berita: ${e.message}")
            }
        }
    }
}