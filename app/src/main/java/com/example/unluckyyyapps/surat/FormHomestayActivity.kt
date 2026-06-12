package com.example.unluckyyyapps.surat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.unluckyyyapps.data.database.AppDatabase
import com.example.unluckyyyapps.data.entity.PengajuanHomestay
import com.example.unluckyyyapps.databinding.ActivityFormHomestayBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormHomestayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormHomestayBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormHomestayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSimpan.setOnClickListener {
            val namaPendaftar = binding.etNamaPendaftar.text.toString().trim()
            val namaHomestay = binding.etNamaHomestay.text.toString().trim()
            val alamat = binding.etAlamat.text.toString().trim()
            val harga = binding.etHarga.text.toString().trim()

            if (namaPendaftar.isEmpty()) {
                binding.etNamaPendaftar.error = "Nama pemilik tidak boleh kosong"
                binding.etNamaPendaftar.requestFocus()
                return@setOnClickListener
            }

            if (namaHomestay.isEmpty()) {
                binding.etNamaHomestay.error = "Nama homestay tidak boleh kosong"
                binding.etNamaHomestay.requestFocus()
                return@setOnClickListener
            }

            if (alamat.isEmpty()) {
                binding.etAlamat.error = "Alamat tidak boleh kosong"
                binding.etAlamat.requestFocus()
                return@setOnClickListener
            }

            if (harga.isEmpty()) {
                binding.etHarga.error = "Harga per malam tidak boleh kosong"
                binding.etHarga.requestFocus()
                return@setOnClickListener
            }

            val pengajuan = PengajuanHomestay(
                namaPendaftar = namaPendaftar,
                namaHomestay = namaHomestay,
                alamat = alamat,
                hargaPerMalam = harga,
                tanggal = getCurrentDate()
            )

            lifecycleScope.launch {
                try {
                    database.pengajuanHomestayDao().insert(pengajuan)
                    Toast.makeText(this@FormHomestayActivity, "Pendaftaran homestay berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@FormHomestayActivity, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
