package com.rgoe.thelastairbender.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.rgoe.thelastairbender.R
import com.rgoe.thelastairbender.databinding.ActivityCharacterBinding
import com.rgoe.thelastairbender.viewmodels.CharacterActivityViewModal
import com.rgoe.thelastairbender.viewmodels.MainActivityViewModel

@BindingAdapter("android:imageUrl")
fun loadImageCharacter(view: ImageView, url: String?) {
    if(url.isNullOrEmpty()) return
    Glide.with(view.context)
        .load(url)
        .into(view)
}

class CharacterActivity : AppCompatActivity() {

    val characterActivityViewModal : CharacterActivityViewModal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCharacterBinding =  DataBindingUtil.setContentView(this, R.layout.activity_character)

        var id: String = intent.extras?.getString("characterID").toString()

        characterActivityViewModal.characterLiveData.observe(this, Observer { character->
            binding.characterByID=character
            binding.executePendingBindings()
        })
        characterActivityViewModal.getCharacterByID(id)


    }
}