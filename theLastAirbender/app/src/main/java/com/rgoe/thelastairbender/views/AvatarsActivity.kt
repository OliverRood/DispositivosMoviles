package com.rgoe.thelastairbender.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rgoe.thelastairbender.R
import com.rgoe.thelastairbender.adapters.AvatarsAdapter
import com.rgoe.thelastairbender.viewmodels.AvatarsActivityViewModel
import com.rgoe.thelastairbender.viewmodels.MainActivityViewModel

@BindingAdapter("android:imageUrlAvatar")
fun loadImageAvatar(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

class AvatarsActivity : AppCompatActivity() {

    val avatarsActivityViewModel : AvatarsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatars)

        val recyclerViewAvatars = findViewById<RecyclerView>(R.id.rvAvatars)
        val avatarsAdapter = AvatarsAdapter()

        recyclerViewAvatars.layoutManager=LinearLayoutManager(this)
        recyclerViewAvatars.adapter=avatarsAdapter

        avatarsActivityViewModel.avatarsLiveData.observe(this,
            Observer { it ->
                avatarsAdapter.addAvatars(it)
                avatarsAdapter.notifyDataSetChanged()
        })
        avatarsActivityViewModel.getAvatars()

    }
}