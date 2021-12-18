package com.sanjay.exo_player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sanjay.exo_player.adapters.RecyclerViewScrollListener
import com.sanjay.exo_player.adapters.VideosAdapter
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension
import com.sanjay.exo_player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding? = null
    val mVideosAdapter by lazy {
        VideosAdapter(ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            executePendingBindings()
            lifecycleOwner = this@MainActivity
        }



    }




}