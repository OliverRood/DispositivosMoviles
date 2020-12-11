package com.rockbass2560.mycamera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FilenameFilter


class ImagePager : AppCompatActivity() {

    private lateinit var file : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_pager)

        val folderImage = File(filesDir, "image")
        val files = folderImage.listFiles(FilenameFilter { dir, name ->
            name.contains("image_")
        })?.reversed()

        val viewPager = findViewById<ViewPager2>(R.id.viewpager)

        val photoPagerAdapter = PhotoPagerAdapter(this, files!!)

        viewPager.adapter = photoPagerAdapter

        val whatsappButton = findViewById<FloatingActionButton>(R.id.fabWhatsApp)
        whatsappButton.setOnClickListener {

            file = files[viewPager.currentItem]
            var imgUri = FileProvider.getUriForFile(
                applicationContext,
                BuildConfig.APPLICATION_ID,
                file)
            var waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            waIntent.setPackage("com.whatsapp")
            waIntent.putExtra(Intent.EXTRA_TEXT, "Enviado desde camera2 app")
            waIntent.putExtra(Intent.EXTRA_STREAM, imgUri)
            waIntent.type = "image/jpeg"
            waIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            try {
                startActivity(waIntent)
            } catch (e: ActivityNotFoundException){
                Toast.makeText(this, "WhatsApp no instalado", Toast.LENGTH_LONG).show()
            }
        }
    }

    private inner class PhotoPagerAdapter(fa: FragmentActivity, private val files: List<File>) :
        FragmentStateAdapter(fa) {
        val cacheFragments = mutableMapOf<Int, Fragment>()

        override fun getItemCount(): Int = files.size

        override fun createFragment(position: Int): Fragment {
            val imagePageFragment: Fragment
            if (!cacheFragments.containsKey(position)) {
                val file = files[position]
                imagePageFragment = ImagePageFragment(file)
                cacheFragments[position] = imagePageFragment
            } else {
                imagePageFragment = cacheFragments[position]!!
            }

            return imagePageFragment
        }

    }
}