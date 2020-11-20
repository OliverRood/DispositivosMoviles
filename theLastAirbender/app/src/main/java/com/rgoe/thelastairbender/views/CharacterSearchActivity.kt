package com.rgoe.thelastairbender.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.rgoe.thelastairbender.BASE_URL
import com.rgoe.thelastairbender.R
import com.rgoe.thelastairbender.adapters.CharacterSearchAdapter
import com.rgoe.thelastairbender.viewmodels.CharacterSearchActivityViewModel

@BindingAdapter("imageUrl")
fun loadImageSearch(view: ImageView, url: String?) {
    if(url.isNullOrEmpty()) return
    Glide.with(view.context)
        .load(url)
        .into(view)
}

class CharacterSearchActivity : AppCompatActivity() {

    val characterSearchActivityViewModel : CharacterSearchActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_search)

        val recyclerViewCharacterSearch = findViewById<RecyclerView>(R.id.rvCharacterSearch)
        val characterSearchAdapter = CharacterSearchAdapter()

        recyclerViewCharacterSearch.layoutManager=LinearLayoutManager(this)
        recyclerViewCharacterSearch.adapter=characterSearchAdapter

        characterSearchActivityViewModel.characterSearchLiveData.observe(this, Observer { it ->
            characterSearchAdapter.addCharacterSearch(it)
            characterSearchAdapter.notifyDataSetChanged()
        })

        var inputText = findViewById<TextInputLayout>(R.id.tilName)
        var name = inputText.editText.toString()
        val mbSearch = findViewById<MaterialButton>(R.id.mbSearch)
        mbSearch.setOnClickListener{
            characterSearchActivityViewModel.getCharacterSearch(name)
        }

    }
}