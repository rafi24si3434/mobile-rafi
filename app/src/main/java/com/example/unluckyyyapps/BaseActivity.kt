package com.example.unluckyyyapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.More.MoreFragment
import com.example.unluckyyyapps.Home.HomeFragment
import com.example.unluckyyyapps.Message.MessageFragment
import com.example.unluckyyyapps.List.ListFragment
import com.example.unluckyyyapps.surat.PengajuanFragment
import com.example.unluckyyyapps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ CEK LOGIN DI AWAL
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (!isLogin) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Fragment default (biar tidak reload terus)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.list -> {
                    replaceFragment(ListFragment())
                    true
                }

                R.id.pengajuan -> {
                    replaceFragment(PengajuanFragment())
                    true
                }

                R.id.message -> {
                    // "About" menu loads MoreFragment (About Bina Desa)
                    replaceFragment(MoreFragment())
                    true
                }

                R.id.more -> {
                    // "Profile" menu loads MessageFragment (Profile Kontributor)
                    replaceFragment(MessageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}