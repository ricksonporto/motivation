package com.ricksonporto.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ricksonporto.motivation.infra.MotivationConstants
import com.ricksonporto.motivation.R
import com.ricksonporto.motivation.infra.SecurityPreferences
import com.ricksonporto.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private var binding: ActivityUserBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater).apply { setContentView(root) }

        onClick()
        //Esconder barra navegação
        supportActionBar?.hide()

        verifyUserName()
    }

    //Eventos
    private fun onClick() {
        binding?.buttonSave?.setOnClickListener {
            if (it.id == R.id.button_save) {
                handleSave()
            }
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = binding?.editWhatName?.text.toString()

        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(applicationContext, R.string.validation_mandatory_name, Toast.LENGTH_SHORT)
                .show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}