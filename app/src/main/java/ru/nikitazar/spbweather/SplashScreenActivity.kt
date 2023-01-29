package ru.nikitazar.spbweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint

private const val DELAY = 1000L

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logoAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.intro_alpha_anim)
        findViewById<TextView>(R.id.app_name).startAnimation(logoAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }, DELAY)
    }
}