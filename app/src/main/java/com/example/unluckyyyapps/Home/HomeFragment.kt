package com.example.unluckyyyapps.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unluckyyyapps.BinaDesa
import com.example.unluckyyyapps.BinaDesaWebView
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.SplashScreenActivity
import com.example.unluckyyyapps.databinding.FragmentHomeBinding
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

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TAMPILKAN NAMA DARI SHAREDPREFERENCES
        val sharedPref = requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
        val nama = sharedPref.getString("nama", null) ?: sharedPref.getString("loginUsername", "Rafi")
        binding.tvGreeting.text = "Halo, $nama! 👋"

        // =========================
        // SETUP NEWS RECYCLERVIEW
        // =========================

        newsAdapter = NewsAdapter()
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = newsAdapter

        // Fetch berita dari API publik
        fetchNews()

        // =========================
        // PERTEMUAN
        // =========================

        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        binding.btnPertemuan4.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // =========================
        // TUGAS P6 - BINA DESA
        // =========================

        binding.btnTugasP6.setOnClickListener {
            startActivity(Intent(requireContext(), BinaDesa::class.java))
        }

        binding.btnWebTugasP6.setOnClickListener {
            startActivity(Intent(requireContext(), BinaDesaWebView::class.java))
        }

        // =========================
        // LOGOUT
        // =========================

        binding.btnLogout.setOnClickListener {
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
                    val limit = minOf(articles.length(), 5)

                    for (i in 0 until limit) {
                        val article = articles.getJSONObject(i)
                        val source = article.getJSONObject("source")

                        val title = article.optString("title", "")
                        val description = if (article.isNull("description")) null else article.getString("description")
                        val urlToImage = if (article.isNull("urlToImage")) null else article.getString("urlToImage")
                        val articleUrl = article.optString("url", "")
                        val publishedAt = article.optString("publishedAt", "")
                        val sourceName = source.optString("name", "Unknown")

                        if (title.isNotEmpty() && title != "null") {
                            list.add(
                                News(
                                    title = title,
                                    description = description,
                                    urlToImage = urlToImage,
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}