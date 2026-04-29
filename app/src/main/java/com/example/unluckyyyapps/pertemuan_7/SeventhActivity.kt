package com.example.unluckyyyapps.pertemuan_7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeventhBinding


    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(
                binding.fragmentContainer.id,
                fragment
            )
            .addToBackStack(null)
            .commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }


        // Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Pertemuan 7"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
        }


        binding.btnfragment1.setOnClickListener {
            replaceFragment(SatuFragment())
        }

        binding.btnfragment2.setOnClickListener {
            replaceFragment(DuaFragment())
        }

        binding.btnfragment3.setOnClickListener {
            replaceFragment(TigaFragment())
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}