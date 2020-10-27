package com.rgoe.bichofans.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rgoe.bichofans.R
import com.rgoe.bichofans.adapters.FanAdapter
import com.rgoe.bichofans.models.roomdb.FanDB

class FansView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fans_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFans)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fanDB = FanDB.getInstance(this)
        val fanDAO = fanDB.fanDAO()
        val fans = fanDAO.getAllFans()

        val fanAdapter = FanAdapter(fans)
        recyclerView.adapter=fanAdapter
        fanAdapter.notifyDataSetChanged()

    }
}