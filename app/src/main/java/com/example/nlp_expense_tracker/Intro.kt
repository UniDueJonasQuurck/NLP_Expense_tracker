package com.example.nlp_expense_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView

class Intro : AppCompatActivity() {
    private lateinit var hintergrund: ImageView
    private lateinit var splasha: LottieAnimationView
    private lateinit var app_name:ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        hintergrund = findViewById(R.id.hintergrund)
        app_name = findViewById(R.id.app_name)
        splasha = findViewById(R.id.splasha)

        splasha.animate().translationY(-1600F).setStartDelay(4000).setDuration(2400)
        app_name.animate().translationY(1400F).setDuration(1700).setStartDelay(4000)
        hintergrund.animate().translationX(-1400F).setDuration(1500).setStartDelay(4500)

        Handler().postDelayed({
            val intent = Intent(this@Intro, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 6200)



    }
}