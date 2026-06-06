package com.example.unluckyyyapps.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.unluckyyyapps.AuthActivity
import com.example.unluckyyyapps.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            OnboardingItem(
                "🏡",
                "Selamat Datang di Bina Desa 👋",
                "Langkah awal untuk mengeksplorasi keindahan desa, mendata pariwisata lokal, dan memajukan perekonomian daerah secara digital dan terintegrasi."
            ),
            OnboardingItem(
                "🛌",
                "Temukan Homestay Nyaman 🛌",
                "Nikmati kehangatan dan keramahan pedesaan dengan pilihan penginapan terbaik yang aman, bersih, dan berfasilitas lengkap untuk liburan Anda."
            ),
            OnboardingItem(
                "🗺️",
                "Dukung Ekonomi Lokal 🗺️",
                "Tiap kunjungan dan kontribusi Anda sangat berharga. Mari bersama-sama memberdayakan potensi wisata lokal demi masa depan desa yang lebih sejahtera."
            )
        )

        adapter = OnboardingAdapter(items)
        binding.viewPagerOnboarding.adapter = adapter

        // Attach WormDotsIndicator to ViewPager2
        binding.dotsIndicator.attachTo(binding.viewPagerOnboarding)

        binding.viewPagerOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == items.size - 1) {
                    binding.btnNext.text = "Ayo Mulai"
                    binding.btnSkip.visibility = View.INVISIBLE
                } else {
                    binding.btnNext.text = "Lanjut"
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        })

        binding.btnSkip.setOnClickListener {
            finishOnboarding()
        }

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPagerOnboarding.currentItem
            if (currentItem + 1 < items.size) {
                binding.viewPagerOnboarding.currentItem = currentItem + 1
            } else {
                finishOnboarding()
            }
        }
    }

    private fun finishOnboarding() {
        // Simpan flag bahwa onboarding sudah pernah dilihat
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        sharedPref.edit().putBoolean("isFirstTime", false).apply()

        // Pindah ke login screen
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
