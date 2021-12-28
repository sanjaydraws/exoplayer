package com.sanjay.exo_player.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.sanjay.exo_player.VideoData
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension.Companion.loadVideo
import com.sanjay.exo_player.databinding.ItemInstagramSuggestedReelsBinding


class FacebookVideoPagerAdapter(var layouts:IntArray ): PagerAdapter() {
    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(layouts[position],container, false)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    inner class VideoViewHolder(private val binding: ItemInstagramSuggestedReelsBinding): RecyclerView.ViewHolder(binding.root) {
        fun setupData(videoData: VideoData) {
            binding.videoPlayerView.loadVideo(videoUrl = videoData.url, item_index = layoutPosition)


        }
    }

}