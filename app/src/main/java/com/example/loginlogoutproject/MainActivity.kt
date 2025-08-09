package com.example.loginlogoutproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginlogoutproject.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //Nama  : Genta Buana
    //NIM   : 10119369
    //Kelas : AKBUL-1


    private lateinit var binding : ActivityMainBinding
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStoreManager = DataStoreManager(this)

        lifecycleScope.launch{
            dataStoreManager.userDataFlow.collectLatest { userData ->
                if (userData == null){
                    navigateToLogin()
                }else{
                    binding.txtNama.text = "Nama Anda : ${userData.nama}"
                    binding.txtEmail.text = "Email Anda : ${userData.email}"
                    binding.txtNoHp.text = "No Hp Anda : ${userData.nohp}"
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch{
                dataStoreManager.clearUserData()
            }
        }

    }

    private fun navigateToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}