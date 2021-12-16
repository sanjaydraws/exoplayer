package com.sanjay.exo_player.bindingAdapter

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView

// extension function for show toast
fun Context.toast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

class PlayerViewExtension {
    companion object{
        // to hold all player
        private var playersMap: MutableMap<Int, SimpleExoPlayer>  = mutableMapOf()
        // for hold currentPlayer
        private  var currentPlayingVideo: Pair<Int, SimpleExoPlayer?>? = null
        // for get current and previous index

        // for get current and previous index

        fun releaseAllPlayers(){
            playersMap.map {
                it.value.stop()

            }
        }

        fun initializePlayer(){
            playersMap.map {
                it.value.prepare()
            }
        }
        // call when scroll to pause any playing player
//        fun pauseCurrentPlayingVideo() {
//            for (entry in playersMap) {
//                if (entry.value.isPlaying) {
//                    entry.value.playWhenReady = false
//                }
//            }
//        }
        // play single video and pause all
        fun playCurrentPlayingVideo(index:Int){
            for(k in playersMap.keys){
                playersMap[k]?.playWhenReady = k == index
            }
        }


        // call when item recycled to improve performance
        fun releaseRecycledPlayers(index: Int){
            playersMap[index]?.release()
        }

//         call when scroll to pause any playing player
        fun pauseCurrentPlayingVideo(){
            if (currentPlayingVideo != null){
                currentPlayingVideo?.second?.playWhenReady = false
            }
        }

        fun playIndexThenPausePreviousPlayer(index: Int){
            if (playersMap.get(index)?.playWhenReady == false) {
                pauseCurrentPlayingVideo()
                playersMap.get(index)?.playWhenReady = true
                currentPlayingVideo = Pair(index, playersMap.get(index))
            }
        }
        fun pausePreviousPlayer(index:Int){
            if (playersMap.get(index)?.playWhenReady == false) {
                pauseCurrentPlayingVideo()
//                playersMap.get(index)?.playWhenReady = true
                currentPlayingVideo = Pair(index, playersMap.get(index))
            }
        }




        @JvmStatic
        @BindingAdapter(
            "app:loadVideo",
            "app:autoPlay",
            "app:item_index",
            requireAll = false
        )
        fun PlayerView.loadVideo(videoUrl:String?=null, autoplay:Boolean? = false, item_index:Int? = null ){
            val source = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
            if(videoUrl == null)
                return
            var simpleExoplayer: SimpleExoPlayer? = null
            // initialize the player
            simpleExoplayer = SimpleExoPlayer.Builder(context).build()

            //autoplay
            if (autoplay != null) {
                simpleExoplayer.playWhenReady = autoplay
            }
            try{

                val mediaItem = MediaItem.fromUri(videoUrl)
                simpleExoplayer?.addMediaItem(mediaItem) // add media item
                // When changing track, retain the latest frame instead of showing a black screen
                setKeepContentOnPlayerReset(true)

                //prepare url
                simpleExoplayer?.prepare()
                this.player = simpleExoplayer
            }catch (e:Exception){

            }

            //    simpleExoplayer?.play()

            //add item index
            // add player with its index to map
            if (playersMap.containsKey(item_index))
                playersMap.remove(item_index)
            if (item_index != null)
                playersMap[item_index] = simpleExoplayer



            simpleExoplayer.addListener(object : Player.EventListener{

                override fun onTimelineChanged(timeline: Timeline, reason: Int) {
                    super.onTimelineChanged(timeline, reason)
                }

                override fun onIsLoadingChanged(isLoading: Boolean) {
                    super.onIsLoadingChanged(isLoading)
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    if(isPlaying){
                        item_index?.let { playCurrentPlayingVideo(it) }
                    }
                    Log.d("onIsPlayingChanged", "onIsPlayingChanged: $isPlaying")
                }

                override fun onTracksChanged(
                    trackGroups: TrackGroupArray,
                    trackSelections: TrackSelectionArray
                ) {
                    super.onTracksChanged(trackGroups, trackSelections)
                }

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    super.onPlayerStateChanged(playWhenReady, playbackState)
                    when (playbackState) {
                        Player.STATE_ENDED -> {
                            Log.i("EventListenerState", "Playback ended!")
                            simpleExoplayer?.playWhenReady = false
                        }
                        Player.STATE_READY -> {
                            Log.i("EventListenerState", "Playback State Ready!")
                        }
                        Player.STATE_BUFFERING -> {
                            simpleExoplayer?.playWhenReady = true
                            Log.i("EventListenerState", "Playback buffering")
//                        binding?.progressBar?.visibility = View.VISIBLE
                        }
                        Player.STATE_IDLE -> {
                        }
                    }
                }
                override fun onRepeatModeChanged(repeatMode: Int) {}
                override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}
                override fun onPlayerError(error: PlaybackException) {
                    super.onPlayerError(error)
//                    this@loadVideo.context.toast("Oops! Error occurred while playing media.")
                }
                override fun onPositionDiscontinuity(reason: Int) {}
                override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {}
                override fun onSeekProcessed() {}
            })

        }

    }
}