package com.rgoe.thelastairbender.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rgoe.thelastairbender.R
import com.rgoe.thelastairbender.adapters.CharactersAdapter
import com.rgoe.thelastairbender.listeners.OnBottomReachedListener
import com.rgoe.thelastairbender.viewmodels.MainActivityViewModel

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

class CharactersActivity : AppCompatActivity() {

    val mainActivityViewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val recyclerViewData = findViewById<RecyclerView>(R.id.rvCharacters)
        val charactersAdapter = CharactersAdapter()

        recyclerViewData.layoutManager=LinearLayoutManager(this)
        recyclerViewData.adapter = charactersAdapter

        mainActivityViewModel.lastAirbenderListLiveData.observe(this,
        Observer { it ->
            charactersAdapter.addResults(it)
            charactersAdapter.notifyDataSetChanged()
        })
        mainActivityViewModel.getCharacters()

        /*
        charactersAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
            override fun onBottomReached() {
                mainActivityViewModel.getCharacters()
            }
        })
         */

        val fabSearch = findViewById<FloatingActionButton>(R.id.fabCharacterSearch)
        fabSearch.setOnClickListener {
            val searchIntent = Intent(this, CharacterSearchActivity::class.java)
            startActivity(searchIntent)
        }
    }
}