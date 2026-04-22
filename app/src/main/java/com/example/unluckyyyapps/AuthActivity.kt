package com.example.unluckyyyapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unluckyyyapps.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Cek apakah user sudah login
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Tombol Login
        binding.btnLogin.setOnClickListener {

            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.edtUsername.error = "Username wajib diisii"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }
            if (username == "admin" && password == "12345") {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(
                    this,
                    "Login Berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Username atau Password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}