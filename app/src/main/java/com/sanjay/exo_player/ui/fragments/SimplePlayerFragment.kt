package com.sanjay.exo_player.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sanjay.exo_player.R
import com.sanjay.exo_player.VideoData
import com.sanjay.exo_player.adapters.RecyclerViewScrollListener
import com.sanjay.exo_player.adapters.VideosAdapter
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension
import com.sanjay.exo_player.databinding.FragmentHomeBinding
import com.sanjay.exo_player.databinding.FragmentSimplePlayerBinding
import com.sanjay.exo_player.ui.fragments.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [SimplePlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimplePlayerFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding: FragmentSimplePlayerBinding? = null
    val mVideosAdapter by lazy {
        VideosAdapter(ArrayList())
    }
    private lateinit var scrollListener: RecyclerViewScrollListener

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SimplePlayerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SimplePlayerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

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
        binding = FragmentSimplePlayerBinding.inflate(layoutInflater, container, false)
        binding?.apply {
            lifecycleOwner =this@SimplePlayerFragment
            executePendingBindings()
        }
        return binding?.root
    }


    override fun initViews(view: View) {
        binding?.rcVideosPlayer?.adapter = mVideosAdapter
        mVideosAdapter.updatedata(videosList = getVideosList())

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