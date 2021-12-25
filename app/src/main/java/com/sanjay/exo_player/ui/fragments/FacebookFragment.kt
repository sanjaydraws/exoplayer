package com.sanjay.exo_player.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.sanjay.exo_player.VideoData
import com.sanjay.exo_player.adapters.FbVideoAdapter
import com.sanjay.exo_player.utils.RecyclerViewScrollListener
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension
import com.sanjay.exo_player.databinding.FragmentFacebookBinding
import com.sanjay.exo_player.ui.fragments.base.BaseFragment
import com.sanjay.exo_player.utils.ViewpagerPageListener

/**
 * A simple [Fragment] subclass.
 * Use the [FacebookFragment.newInstance] factory method to
 * create an instance of this fragment .
 */
class FacebookFragment : BaseFragment() {
    var binding:FragmentFacebookBinding? = null
    private lateinit var pagerListener:ViewpagerPageListener

    private val mFbVideosAdapter by lazy {
        FbVideoAdapter(ArrayList())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFacebookBinding.inflate(layoutInflater, container, false)
        binding?.apply {
            this.lifecycleOwner = this@FacebookFragment
            executePendingBindings()
        }
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FacebookFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    override fun initViews(view: View) {
        binding?.viewPager?.adapter = mFbVideosAdapter
        mFbVideosAdapter.updatedata(videosList = getVideosList())
    }

    override fun setupListener() {
        pagerListener = object :ViewpagerPageListener(){
            override fun onCurrentVisibleItem(index: Int?) {
                Log.d(TAG, "onCurrentVisibleItem: $index")
            }

            override fun onCurrentVisibleItemState(@ScrollState state: Int?) {
                Log.d(TAG, "onCurrentVisibleItemState: $state")
            }
        }

        binding?.viewPager?.registerOnPageChangeCallback(pagerListener)
    }

    override fun initObservers() {
    }

    override fun loadData() {
    }


    fun getVideosList():ArrayList<VideoData>{
        val l:ArrayList<VideoData> = ArrayList()
        l.add(VideoData(0, title = "Spiderman", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4", thumbnail = ""))
        l.add(VideoData(1, title = "ironman", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4", thumbnail = ""))
        l.add(VideoData(2, title = "Spiderman2", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4", thumbnail = ""))
        l.add(VideoData(3, title = "superman", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4", thumbnail = ""))
        l.add(VideoData(4, title = "no way home", url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4", thumbnail = ""))
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