package com.rgoe.thelastairbender.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.rgoe.thelastairbender.R
import com.rgoe.thelastairbender.databinding.ActivityMainBinding
import com.rgoe.thelastairbender.viewmodels.MainActivityViewModel

@BindingAdapter("android:imageUrlRandom")
fun loadImageRandom(view: ImageView, url: String?) {
    if(url.isNullOrEmpty()) return
    Glide.with(view.context)
        .load(url)
        .into(view)
}

class MainActivity : AppCompatActivity() {
    val mainActivityViewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel.lastAirbenderRandomLiveData.observe(this, Observer {random ->
            Log.d("Prueba Random", random.toString())
            binding.character=random.get(0)
            binding.executePendingBindings()
        })
        mainActivityViewModel.getCharacterRandom()

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