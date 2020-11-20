package com.rgoe.thelastairbender.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rgoe.thelastairbender.R
import com.rgoe.thelastairbender.databinding.ActivityCharacterBinding

class CharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCharacterBinding =  DataBindingUtil.setContentView(this, R.layout.activity_character)


    }
}