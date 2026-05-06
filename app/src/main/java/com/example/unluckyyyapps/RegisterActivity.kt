package com.example.unluckyyyapps

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unluckyyyapps.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agamaList = arrayOf(
            "Pilih Agama",
            "Islam",
            "Kristen",
            "Katolik",
            "Hindu",
            "Buddha",
            "Konghucu"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            agamaList
        )

        binding.spAgama.adapter = adapter

        binding.etTanggal.setOnClickListener {
            showDatePicker()
        }

        binding.btnRegister.setOnClickListener {
            validasiRegister()
        }
    }

    private fun showDatePicker() {

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, y, m, d ->
                binding.etTanggal.setText("$d/${m + 1}/$y")
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun validasiRegister() {

        // RESET ERROR
        binding.etNama.error = null
        binding.etTanggal.error = null
        binding.etUsername.error = null
        binding.etPassword.error = null
        binding.etConfirmPassword.error = null
        binding.rbPerempuan.error = null

        val nama = binding.etNama.text.toString().trim()
        val tanggal = binding.etTanggal.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        val jenisKelamin = when {
            binding.rbLaki.isChecked -> "Laki-Laki"
            binding.rbPerempuan.isChecked -> "Perempuan"
            else -> ""
        }

        val agama = binding.spAgama.selectedItem.toString()

        when {

            nama.isEmpty() -> {
                binding.etNama.error = "Nama wajib diisi"
            }

            tanggal.isEmpty() -> {
                binding.etTanggal.error = "Tanggal lahir wajib diisi"
            }

            jenisKelamin.isEmpty() -> {
                binding.rbPerempuan.error = "Pilih jenis kelamin"
            }

            agama == "Pilih Agama" -> {

                val errorText =
                    binding.spAgama.selectedView as TextView

                errorText.error = "Pilih agama"
                errorText.text = "Pilih agama"
            }

            username.isEmpty() -> {
                binding.etUsername.error = "Username wajib diisi"
            }

            password.isEmpty() -> {
                binding.etPassword.error = "Password wajib diisi"
            }

            confirmPassword.isEmpty() -> {
                binding.etConfirmPassword.error =
                    "Confirm password wajib diisi"
            }

            password != confirmPassword -> {
                binding.etConfirmPassword.error =
                    "Password tidak sama"
            }

            else -> {

                val sharedPref =
                    getSharedPreferences(
                        "user_pref",
                        Context.MODE_PRIVATE
                    )

                val editor = sharedPref.edit()

                editor.putString("nama", nama)
                editor.putString("tanggal", tanggal)
                editor.putString("jenis_kelamin", jenisKelamin)
                editor.putString("agama", agama)
                editor.putString("username", username)
                editor.putString("password", password)

                editor.apply()

                Toast.makeText(
                    this,
                    "Registrasi berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}