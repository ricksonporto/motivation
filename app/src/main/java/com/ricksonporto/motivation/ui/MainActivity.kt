package com.ricksonporto.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ricksonporto.motivation.infra.MotivationConstants
import com.ricksonporto.motivation.infra.SecurityPreferences
import com.ricksonporto.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        onClick()
        //Esconder barra
        supportActionBar?.hide()
        handleUserName()
    }

    //Eventos
    private fun onClick() {
        binding?.buttonNewPhrase?.setOnClickListener {

        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding?.textUserName?.text = "Olá, $name!"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}