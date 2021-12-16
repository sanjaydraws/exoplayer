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
    private lateinit var scrollListener: RecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            executePendingBindings()
            lifecycleOwner = this@MainActivity
        }
        binding?.rcVideosPlayer?.adapter = mVideosAdapter
        mVideosAdapter.updatedata(videosList = getVideosList())

        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                Log.d("scrollListener", index.toString())
                // play just visible item
                if (index != -1) {
                    PlayerViewExtension.pausePreviousPlayer(index)
//                    PlayerViewExtension.playIndexThenPausePreviousPlayer(index)
                }
            }

        }
        binding?.rcVideosPlayer?.addOnScrollListener(scrollListener)



    }


    fun getVideosList():ArrayList<VideoData>{
        val l:ArrayList<VideoData> = ArrayList()
        l.add(VideoData(0, title = "Spiderman", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(1, title = "ironman", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(2, title = "Spiderman2", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(3, title = "superman", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(4, title = "no way home", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(5, title = "Avengers", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(6, title = "Avengers2", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(7, title = "Ironman2", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(8, title = "Thor", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(9, title = "Sha", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        l.add(VideoData(10, title = "Trai", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", thumbnail = ""))
        return l
    }

    override fun onStop() {
        super.onStop()
        PlayerViewExtension.releaseAllPlayers()
    }

    override fun onPause() {
        super.onPause()
        PlayerViewExtension.releaseAllPlayers()
    }
}