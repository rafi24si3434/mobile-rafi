package com.example.unluckyyyapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unluckyyyapps.databinding.ActivityAuthBinding
import com.example.unluckyyyapps.RegisterActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {

            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.edtUsername.error = "Username wajib diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }

            // AMBIL DATA DARI REGISTER
            val savedUsername = sharedPref.getString("username", "")
            val savedPassword = sharedPref.getString("password", "")

            // RULE 1
            if (username == password) {

                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("loginUsername", username)
                editor.apply()

                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()

            }

            // RULE 2
            else if (username == savedUsername && password == savedPassword) {

                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("loginUsername", username)
                editor.apply()

                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Username atau Password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // PINDAH KE REGISTER
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}