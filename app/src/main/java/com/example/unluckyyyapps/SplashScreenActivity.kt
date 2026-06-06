package com.example.unluckyyyapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.unluckyyyapps.onboarding.OnboardingActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_splash_screen
        )

        lifecycleScope.launch {

            delay(2000)

            val sharedPref =
                getSharedPreferences(
                    "user_pref",
                    MODE_PRIVATE
                )

            val isFirstTime =
                sharedPref.getBoolean(
                    "isFirstTime",
                    true
                )

            val isLogin =
                sharedPref.getBoolean(
                    "isLogin",
                    false
                )

            val intent =
                if (isFirstTime) {
                    Intent(
                        this@SplashScreenActivity,
                        OnboardingActivity::class.java
                    )
                } else if (isLogin) {
                    Intent(
                        this@SplashScreenActivity,
                        BinaDesa::class.java
                    )
                } else {
                    Intent(
                        this@SplashScreenActivity,
                        AuthActivity::class.java
                    )
                }

            startActivity(intent)
            finish()
        }
    }
}