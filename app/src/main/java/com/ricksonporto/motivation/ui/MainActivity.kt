package com.ricksonporto.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ricksonporto.motivation.R
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
        handleFilter(R.id.image_all)
    }

    //Eventos
    private fun onClick() {
        binding?.buttonNewPhrase?.setOnClickListener {}
        binding?.imageAll?.setOnClickListener {}
        binding?.imageHappy?.setOnClickListener {}
        binding?.imageSunny?.setOnClickListener {}
    }

    private fun handleFilter(id: Int) {
        binding?.imageAll?.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding?.imageHappy?.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding?.imageSunny?.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding?.imageAll?.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.image_happy -> {
                binding?.imageHappy?.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.image_Sunny -> {
                binding?.imageSunny?.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
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