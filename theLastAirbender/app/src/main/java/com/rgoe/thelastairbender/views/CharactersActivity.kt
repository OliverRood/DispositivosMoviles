package com.rgoe.thelastairbender.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rgoe.thelastairbender.R

class CharactersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val fabSearch = findViewById<FloatingActionButton>(R.id.fabCharacterSearch)
        fabSearch.setOnClickListener {
            val searchIntent = Intent(this, CharacterSearchActivity::class.java)
            startActivity(searchIntent)
        }
    }
}