package com.example.unluckyyyapps.List

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.unluckyyyapps.data.database.AppDatabase
import com.example.unluckyyyapps.databinding.ActivityFormPariwisataBinding
import kotlinx.coroutines.launch

class FormPariwisataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormPariwisataBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPariwisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val deskripsi = binding.etDeskripsi.text.toString().trim()
            var gambarUrl = binding.etGambarUrl.text.toString().trim()

            if (nama.isEmpty()) {
                binding.etNama.error = "Nama tempat wisata tidak boleh kosong"
                binding.etNama.requestFocus()
                return@setOnClickListener
            }

            if (deskripsi.isEmpty()) {
                binding.etDeskripsi.error = "Deskripsi tidak boleh kosong"
                binding.etDeskripsi.requestFocus()
                return@setOnClickListener
            }

            // Set placeholder picsum image if empty
            if (gambarUrl.isEmpty()) {
                val randomId = (100..999).random()
                gambarUrl = "https://picsum.photos/500?random=$randomId"
            }

            val pariwisata = Pariwisata(
                nama = nama,
                deskripsi = deskripsi,
                gambarUrl = gambarUrl
            )

            lifecycleScope.launch {
                try {
                    database.pariwisataDao().insert(pariwisata)
                    Toast.makeText(this@FormPariwisataActivity, "Destinasi pariwisata berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@FormPariwisataActivity, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
