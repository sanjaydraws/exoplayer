package com.sanjay.exo_player.utils

import android.util.Log
import androidx.viewpager2.widget.ViewPager2

abstract class ViewpagerPageListener: ViewPager2.OnPageChangeCallback() {
    companion object{
        const val  TAG ="ViewpagerScrollListener"
    }
    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        Log.d(TAG, "onPageScrollStateChanged: $state")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        Log.d(TAG, "onPageScrolled: position:$position positionOffset${positionOffset}" +
                "positionOffsetPixels $positionOffsetPixels")
    }
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        Log.d(TAG, "onPageSelected: $position")

    }
}