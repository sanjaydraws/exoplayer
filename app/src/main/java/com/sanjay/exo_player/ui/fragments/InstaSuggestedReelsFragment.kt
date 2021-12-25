package com.sanjay.exo_player.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sanjay.exo_player.VideoData
import com.sanjay.exo_player.adapters.InstaSuggestedVideosAdapter
import com.sanjay.exo_player.utils.RecyclerViewScrollListener
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension
import com.sanjay.exo_player.databinding.FragmentInstaSuggestedReelsBinding
import com.sanjay.exo_player.ui.fragments.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [InstaSuggestedReelsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstaSuggestedReelsFragment : BaseFragment() {
    private lateinit var scrollListener: RecyclerViewScrollListener
    private val mSuggestedVideosAdapter by lazy {
        InstaSuggestedVideosAdapter(ArrayList())
    }

    var binding:FragmentInstaSuggestedReelsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstaSuggestedReelsBinding.inflate(layoutInflater, container, false)
        binding?.apply {
            this.lifecycleOwner = this@InstaSuggestedReelsFragment
            executePendingBindings()
        }
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InstaSuggestedReelsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    override fun initViews(view: View) {
        binding?.rcVideosPlayer?.adapter = mSuggestedVideosAdapter
        mSuggestedVideosAdapter.updatedata(videosList = getVideosList())

    }

    override fun setupListener() {

        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                // play just visible item
                if (index != -1) {
                    PlayerViewExtension.pausePreviousPlayer(index)
                    Log.d("SCROLL_LISTENER", "onItemIsFirstVisibleItem: $index")
//                    PlayerViewExtension.playIndexThenPausePreviousPlayer(index)
                }
            }
        }
        binding?.rcVideosPlayer?.addOnScrollListener(scrollListener)


    }

    override fun initObservers() {
    }

    override fun loadData() {
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