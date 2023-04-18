package com.ricksonporto.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ricksonporto.motivation.R
import com.ricksonporto.motivation.data.Mock
import com.ricksonporto.motivation.infra.MotivationConstants
import com.ricksonporto.motivation.infra.SecurityPreferences
import com.ricksonporto.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var categoryId = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        //Esconder barra
        supportActionBar?.hide()
        onClick()
        handleFilter(R.id.image_all)
        handleNextPhrase()
    }

    override fun onStart() {
        super.onStart()
        handleUserName()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun onClick() {
        binding?.buttonNewPhrase?.setOnClickListener { view ->
            if (view.id == R.id.button_new_phrase) {
                handleNextPhrase()
            }
        }
        binding?.imageAll?.setOnClickListener { view -> handleFilter(view.id) }
        binding?.imageHappy?.setOnClickListener { view -> handleFilter(view.id) }
        binding?.imageSunny?.setOnClickListener { view -> handleFilter(view.id) }
    }

    private fun handleNextPhrase() {
        binding?.textPhrase?.text = Mock().getPhrase(categoryId, Locale.getDefault().language)
    }

    private fun handleFilter(id: Int) {
        binding?.imageAll?.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding?.imageHappy?.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding?.imageSunny?.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding?.imageAll?.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_happy -> {
                binding?.imageHappy?.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding?.imageSunny?.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.SUNNY
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