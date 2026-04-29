package com.example.unluckyyyapps.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //setContentView(R.layout.activity_third)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val inputNo: EditText = findViewById(R.id.inputNo)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)


        binding.btnSubmit.setOnClickListener {
            val no = binding.inputNo.text.toString()

            Log.e("Klik btnSubmit", "Tombol ditekan. Isi inputNama = $no")

            Toast.makeText(this, "Anda Telah Menekan Tombol: $no", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ThirdResultActivity::class.java)
            startActivity(intent)
        }

    }
}