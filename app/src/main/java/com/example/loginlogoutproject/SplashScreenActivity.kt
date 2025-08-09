package com.example.loginlogoutproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginlogoutproject.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.view.animation.AnimationUtils
import kotlinx.coroutines.flow.first

class SplashScreenActivity : AppCompatActivity() {

    //Nama  : Genta Buana
    //NIM   : 10119369
    //Kelas : AKBUL-1


    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)

        //Fungsi Slide in
        val slideAnim = AnimationUtils.loadAnimation(this,R.anim.slide_in)
        binding.txtLoading.startAnimation(slideAnim)
        binding.txtNamaAplikasi.startAnimation(slideAnim)
        binding.imgImageLogo.startAnimation(slideAnim)

        lifecycleScope.launch{
            delay(3000)

            val user = dataStoreManager.userDataFlow.first()
            if (user != null) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}