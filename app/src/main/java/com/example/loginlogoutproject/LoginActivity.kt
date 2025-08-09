package com.example.loginlogoutproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginlogoutproject.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch


//Nama  : Genta Buana
//NIM   : 10119369
//Kelas : AKBUL-1

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var dataStoreManager : DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)

        binding.btnLogin.setOnClickListener{
            val nama = binding.editNama.text.toString()
            val email = binding.editEmail.text.toString()
            val nohp = binding.editNoHp.text.toString()

            if(nama.isEmpty()){
                binding.editNama.error = "Nama Tidak Boleh Kosong"
                return@setOnClickListener
            }
            if(email.isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editEmail.error = "Email Tidak Boleh Kosong"
                return@setOnClickListener
            }
            if(nohp.isEmpty()){
                binding.editNoHp.error="No HP Todak Boleh Kosong"
                return@setOnClickListener
            }

            lifecycleScope.launch {
                dataStoreManager.saveUserData(nama,email,nohp)
                navigateToMain()
            }
        }
    }
    private fun navigateToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}