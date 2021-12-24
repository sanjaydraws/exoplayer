package com.sanjay.exo_player.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjay.exo_player.VideoData
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension
import com.sanjay.exo_player.bindingAdapter.PlayerViewExtension.Companion.loadVideo
import com.sanjay.exo_player.databinding.ItemFacebookVideoBinding
import com.sanjay.exo_player.databinding.ItemInstagramSuggestedReelsBinding



class FbVideoAdapter (var videosList: ArrayList<VideoData>) :
    RecyclerView.Adapter<FbVideoAdapter.VideoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ItemFacebookVideoBinding.inflate(inflater,parent,false)
        return  VideoViewHolder(binding)
    }
    fun updatedata(videosList:ArrayList<VideoData>){
        this.videosList = videosList
        notifyDataSetChanged()
    }

    /*called every when scrolled 4 items */
    override fun onViewRecycled(holder: VideoViewHolder) {
        val position = holder.absoluteAdapterPosition
        PlayerViewExtension.releaseRecycledPlayers(position)
        Log.d("TAG", "onViewRecycled: $position  ${videosList[position].title}")
        super.onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        Log.d("TAG", "onAttachedToRecyclerView: $recyclerView")
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        holder.setupData( videosList[position])
    }

    override fun getItemCount(): Int {
        return videosList.size
    }


    inner class VideoViewHolder(private val binding: ItemFacebookVideoBinding): RecyclerView.ViewHolder(binding.root) {
        fun setupData(videoData: VideoData) {
            binding.videoPlayerView.loadVideo(videoUrl = videoData.url, item_index = layoutPosition)


        }
    }

}