package com.sanjay.exo_player.utils

import android.util.Log
import androidx.annotation.IntDef
import androidx.viewpager2.widget.ViewPager2


/**
 * @author sanjay Prajapat
 * time : 26-12-2021 12:00 Am
 * blog: https://dev.to/sanjayprajapat
 * */

abstract class ViewpagerPageListener: ViewPager2.OnPageChangeCallback() {
    companion object{
        const val  TAG ="ViewpagerScrollListener"
        const val SCROLL_STATE_IDLE = 0
        const val SCROLL_STATE_DRAGGING = 1
        const val SCROLL_STATE_SETTLING = 2


    }

    @IntDef(  SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING)
    annotation class ScrollState



    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        Log.d(TAG, "onPageScrollStateChanged: $state")
        onCurrentVisibleItemState(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        Log.d(TAG, "onPageScrolled: position:$position positionOffset${positionOffset}" +
                "positionOffsetPixels $positionOffsetPixels")
        onCurrentVisibleItem(position)
    }
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        Log.d(TAG, "onPageSelected: $position")
    }

    abstract fun onCurrentVisibleItem(index: Int?)
    abstract fun onCurrentVisibleItemState(@ScrollState state:Int?)

}