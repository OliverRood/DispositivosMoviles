package com.rgoe.thelastairbender.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.rgoe.thelastairbender.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val charactersButton = findViewById<MaterialButton>(R.id.mbCharacters)
        charactersButton.setOnClickListener{
            val charactersIntent = Intent(this, CharactersActivity::class.java)
            startActivity(charactersIntent)
        }

        val avatarsButton = findViewById<MaterialButton>(R.id.mbAvatars)
        avatarsButton.setOnClickListener{
            val avatarsIntent = Intent(this, AvatarsActivity::class.java)
            startActivity(avatarsIntent)
        }

        val aboutTxt = findViewById<TextView>(R.id.txtAbout)
        aboutTxt.setOnClickListener{
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }
    }
}